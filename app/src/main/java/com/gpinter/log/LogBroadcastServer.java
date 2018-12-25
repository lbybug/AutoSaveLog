package com.gpinter.log;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import android.util.Log;

public class LogBroadcastServer implements Runnable {

    private static final String TAG = "LogBroadcastServer";

    public static final int BROADCAST_PORT = 8970;

    private Selector mSelector = null;
    private EventHandler mEventHandler = null;
    private ServerSocketNio mSs = null;

    public static final int MSG_KERNEL = 1;
    public static final int MSG_MAIN = 2;
    public static final int MSG_SYSTEM = 3;
    public static final int MSG_RADIO = 4;
    public static final int MSG_EVENTS = 4;

    private static class Msg {
        public int type;
        public byte[] data;

        public Msg(int type, byte[] data, int size) {
            this.type = type;
            this.data = new byte[size];
            System.arraycopy(data, 0, this.data, 0, size);
        }
    }

    private final Object mLock = new Object();
    private final LinkedList<Msg> mMsgList = new LinkedList<Msg>();

    // 所有client操作都在同一线程，所以不需要lock
    private final LinkedList<ClientSocketNio> mClients = new LinkedList<ClientSocketNio>();

    private volatile int mClientSize = 0;

    public LogBroadcastServer() throws IOException {
        mSelector = Selector.open();
        mEventHandler = new EventHandler();
        mSs = new ServerSocketNio(BROADCAST_PORT);

        addChannel(mEventHandler, SelectionKey.OP_READ);
        addChannel(mSs, SelectionKey.OP_ACCEPT);
    }

    @Override
    public void run() {
        while (true) {
            oneLoop();

            Msg m;

            while ((m = popMsg()) != null) {
                int size = mClients.size();
                for (int i = 0; i < size; i++) {
                    ClientSocketNio c = mClients.get(i);
                    try {
                        c.send(m);
                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.e(TAG, e.getMessage());
                        removeClient(c);
                        i--;
                        size--;
                    }
                }
            }
        }
    }

    public void broadcast(int type, byte[] data, int size) {
        synchronized (mLock) {
            if (mClientSize == 0) // 没有连接的客户端就不发送了
                return;

            Log.d(TAG, "braodcast : " + size);
            mMsgList.add(new Msg(type, data, size));
            mEventHandler.writeMessage();
        }
    }

    public Msg popMsg() {
        synchronized (mLock) {
            if (mMsgList.size() > 0) {
                return mMsgList.removeFirst();
            }
        }
        return null;
    }

    private static interface NioObject {
        public SelectableChannel getChannel();

        public void onSelection(SelectionKey key);

        public void close();
    }

    private void addChannel(NioObject obj, int op) throws IOException {
        SelectableChannel ch = obj.getChannel();
        ch.configureBlocking(false);
        ch.register(mSelector, op, obj);
    }

    private void oneLoop() {
        System.out.println("loop...");

        int readyChannels;
        try {
            readyChannels = mSelector.select();
            if (readyChannels == 0)
                return;
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        Set<SelectionKey> selectedKeys = mSelector.selectedKeys();
        Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

        while (keyIterator.hasNext()) {
            SelectionKey key = keyIterator.next();
            System.out.println("event : " + key.channel());

            try {
                NioObject o = (NioObject) key.attachment();
                o.onSelection(key);
            } catch (Throwable e) {
                e.printStackTrace();
            }
            keyIterator.remove();
        }
    }

    private static abstract class PipeNio implements NioObject {

        protected final Pipe mPipe;

        public PipeNio() throws IOException {
            mPipe = Pipe.open();
        }

        @Override
        public SelectableChannel getChannel() {
            return mPipe.source();
        }

        @Override
        public void close() {
            try {
                mPipe.source().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                mPipe.sink().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class EventHandler extends PipeNio {

        public EventHandler() throws IOException {
            super();
        }

        @Override
        public void onSelection(SelectionKey key) {
            System.out.println("EventHandler event");

            if (key.isReadable()) {
                try {
                    ByteBuffer BUFFER = ByteBuffer.allocate(1);
                    mPipe.source().read(BUFFER);
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e(TAG, e.getMessage());
                }
            }
        }

        public void writeMessage() {
            try {
                ByteBuffer EVENT = ByteBuffer.allocate(1);
                EVENT.put((byte) 1);
                EVENT.flip();
                mPipe.sink().write(EVENT);
            } catch (IOException e) {
                e.printStackTrace();
                Log.e(TAG, e.getMessage());
            }
        }
    }

    private class ClientSocketNio implements NioObject {

        private SocketChannel mChannel;

        public ClientSocketNio(SocketChannel s) {
            mChannel = s;
        }

        @Override
        public void close() {
            try {
                mChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public SelectableChannel getChannel() {
            return mChannel;
        }

        public String toString() {
            return mChannel.socket().getRemoteSocketAddress().toString();
        }

        @Override
        public void onSelection(SelectionKey key) {
            ByteBuffer bb = ByteBuffer.allocate(1024);
            try {
                if (mChannel.read(bb) <= 0) {
                    throw new IOException("failed to read.");
                }
            } catch (IOException e) {
                e.printStackTrace();
                Log.e(TAG, e.getMessage());
                removeClient(this);
            }
        }

        public void send(Msg m) throws IOException {
            ByteBuffer bb = ByteBuffer.allocate(m.data.length);
            bb.put(m.data);
            bb.flip();
            mChannel.write(bb);
        }
    }

    private class ServerSocketNio implements NioObject {

        private final ServerSocketChannel mChannel;

        public ServerSocketNio(int port) throws IOException {
            mChannel = ServerSocketChannel.open();
            mChannel.socket().bind(new InetSocketAddress(port));
        }

        @Override
        public SelectableChannel getChannel() {
            return mChannel;
        }

        @Override
        public void close() {
            try {
                mChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onSelection(SelectionKey key) {
            System.out.println("server socket event");
            if (key.isAcceptable()) {
                try {
                    addClient(mChannel.accept());
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void addClient(SocketChannel s) throws IOException {
        ClientSocketNio c = new ClientSocketNio(s);
        System.out.println("add client : " + c.toString());
        mClients.add(c);
        mClientSize = mClients.size();

        addChannel(c, SelectionKey.OP_READ);
    }

    private void removeClient(ClientSocketNio c) {
        System.out.println("remove client : " + c.toString());

        mClients.remove(c);
        mClientSize = mClients.size();

        c.close();
    }
}
