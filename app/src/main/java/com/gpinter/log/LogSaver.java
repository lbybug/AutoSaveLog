package com.gpinter.log;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.DatagramSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.os.SystemClock;
import android.util.Log;

public class LogSaver {

    private static final String TAG = "LogSaver";

    private static final String SVR_NAME = "minipos.logsaver";

    private static final long MAX_TOTAL_FILE_SIZE = 3 * 10 * 1024 * 1024; // 50 MB

    private static final long MAX_LOG_FILE_SIZE = 10 * 1024 * 1024; // 10 MB

    private static final File TEMP_PATH = new File("/data/local/tmp");
    private static final String FILENAME_SEPERATOR = "_";
    private static final String LOGFILE_EXT = ".txt";

    private static final long CHECK_TIMEOUT = 60 * 1000; // 每隔60秒检查一下

    // 每隔多久要重新生成log文件
    private static final long LOG_FILE_SWITCH_TIMEOUT = 10 * 60 * 60 * 1000; // 10小时
    //private static final long LOG_FILE_SWITCH_TIMEOUT = 10*60*1000; // 10分钟

    // 标识多久前的文件要删除
    private static final long TIME_OF_FILE_TO_DELETE = 2 * 24 * 60 * 60 * 1000; // 两天前
    //private static final long TIME_OF_FILE_TO_DELETE = 30*60*1000; // 30分钟

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd_HHmmss", Locale.US);

    public static String getServiceName() {
        return SVR_NAME;
    }


    public static void startSaveLog(String[] args) {

        if (args.length == 2 && "read".equals(args[0])) {
            if ("kmsg".equals(args[1])) {

                new Thread() {
                    @Override
                    public void run() {
                        Socket s = null;
                        try {
                            s = new Socket("localhost", LogBroadcastServer.BROADCAST_PORT);
                            byte[] data = new byte[1024 * 2];
                            int size;
                            while ((size = s.getInputStream().read(data)) > 0) {
                                System.out.write(data, 0, size);
                                System.out.flush();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            if (s != null) {
                                try {
                                    s.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }.start();
            }
        } else {
            System.out.println("press 'q' to exit.");
            start();
            try {
                while (System.in.read() != 'q') {
                    Thread.sleep(100);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressLint("NewApi")
    public static void start() {

//		String s = Environment.getStorageDirectory();
//		System.out.println("storage : " + s);
//		Platform.initSysProp(null);

        LogBroadcastServer bs;
        try {
            //File path = new File(Environment.getExternalStorageDirectory(), "log");
            //File extPath = Storage.getExternalStorageDirectory();
            File extPath = new File("/sdcard/");
            Log.d(TAG, "external storage path : " + extPath);

            File path = new File(extPath, "log");
            bs = new LogBroadcastServer();
//            new Thread(new KmsgSaver(bs, path)).start();
            new Thread(new LogcatSaver(bs, path)).start();
            new Thread(bs).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void copyFile(File src, File dst) throws IOException {
        FileOutputStream os = null;
        FileInputStream is = null;

        try {
            is = new FileInputStream(src);
            os = new FileOutputStream(dst);

            byte[] buff = new byte[1024 * 5];
            int s;
            while ((s = is.read(buff)) > 0) {
                os.write(buff, 0, s);
            }
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private static final Comparator<File> FILE_CMP = new Comparator<File>() {
        @Override
        public int compare(File lhs, File rhs) {
            return (int) (lhs.lastModified() - rhs.lastModified());
        }
    };

    private static abstract class AbstractSaver implements Runnable {

        //private final Object mLock = new Object();
        //private boolean mRunning = false;
        //private volatile boolean mExitPending = false;

        protected final File mStorePath;
        protected final String mName;

        private boolean mStoreInSdcard = true;

        //private long mStartTime = 0;
        private long mSwitchTime = 0;

        private File mFile = null;
        private long mFileSize = 0;

        public AbstractSaver(String name, File path) {
            mName = name;
            mStorePath = path;

            // 检查之前的文件是否超出存储大小
            checkAndRemoveTotalLogFileSize(path);
            checkAndRemoveTotalLogFileSize(TEMP_PATH);
        }

        @Override
        public void run() {

            byte[] buffer = new byte[1024 * 2];

            try {

                mSwitchTime = SystemClock.uptimeMillis() + LOG_FILE_SWITCH_TIMEOUT;
                Log.d(TAG, mName + ", switch time = " + mSwitchTime);

                long checkTime = SystemClock.uptimeMillis() + CHECK_TIMEOUT;

                // 删除太旧的log文件(几天前)
                checkAndRemoveOldFile();

                //while(!mExitPending) {
                while (true) {

                    try {
                        // 每隔 CHECK_TIMEOUT，检查一次
                        long curr = SystemClock.uptimeMillis();
                        if (curr > checkTime) {
                            checkTime = curr + CHECK_TIMEOUT;

							/* 两种情况，临时保存到/data
                             * (1)开机后/mnt/sdcard未挂载
							 * (2)格式sdcard时
							 * 定时检查保存位置，如sdcard挂载后，则转到sdcard
							 */
                            checkStorePath();

                            // 超过指定时间，就新建log文件
                            checkSwitchTime(curr);

                            // 文件超出规定大小，那就重新创建一个文件
                            checkStoreFileSize();
                        }

                        // 保存log
                        int size = read(buffer);
                        if (size > 0)
                            storeLog(buffer, 0, size);

                    } catch (IOException e) {
                        //e.printStackTrace(); // 不要输出太多log了
                        Log.e(TAG, e.getMessage());
                        close();

                        try {
                            // 出错了，等等再保存log
                            Thread.sleep(5000);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                    }
                }

            } finally {
                close();
                //mRunning = false;
            }
        }

        private FileOutputStream mOutputStream = null;

        protected void openLogFile(File fn) throws IOException {


            if (mOutputStream != null) {
                closeLogFile();
            }

            Log.d(TAG, mName + ", open log file : " + fn.getAbsolutePath());
            mOutputStream = new FileOutputStream(fn);
            mFile = fn;
            mFileSize = 0;
        }

        protected void closeLogFile() {
            if (mOutputStream == null)
                return;

            Log.d(TAG, mName + ", close log file.");

            OutputStream os = mOutputStream;
            File f = mFile;

            mOutputStream = null;
            mFile = null;

            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // 有可能是 /data/local/tmp, 或者 /sdcard/log, 所以要用f.getParentFile()
            if (f != null)
                checkAndRemoveTotalLogFileSize(f.getParentFile());
        }

        protected void storeLog(byte[] data, int offset, int size) throws IOException {
            if (mOutputStream == null) {
                openLogFile(genLogFile());
            }

            if (mOutputStream == null) {
                //TODO : 无法保存log，会有部分log丢失
                Log.d(TAG, "failed to open log file. skip write log.");
                //留出空闲时间
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    mOutputStream.write(data, offset, size);
                    mOutputStream.flush();
                    mFileSize += size;

                    checkStoreFileSize();

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                    closeLogFile();
                }
            }
        }

        protected void checkStoreFileSize() throws IOException {

            if (mOutputStream == null || mFile == null) {
                return;
            }

            if (mFileSize < MAX_LOG_FILE_SIZE) {
                return;
            }

            // 先close再genLogFile，因为genLogFile有可能报错
            closeLogFile();
            openLogFile(genLogFile());
        }

        protected void checkSwitchTime(long curr) throws IOException {
            if (curr < mSwitchTime)
                return;

            // 删除太旧的log文件(几天前)
            checkAndRemoveOldFile();

            mSwitchTime = SystemClock.uptimeMillis() + LOG_FILE_SWITCH_TIMEOUT;
            Log.d(TAG, mName + ", switch timeout. create new log file. next time = " + mSwitchTime);

            // 先close再genLogFile，因为genLogFile有可能报错
            closeLogFile();
            openLogFile(genLogFile());
        }

        protected void checkAndRemoveTotalLogFileSize(File path) {
            // 之前已经closeLogFile，所以mFile==null, close前保存mFile

            //Log.d(TAG, "check total size : path="+path);
            try {

                if (path == null)
                    return;

                File[] subs = listStoredLogFile(path);
                if (subs == null || subs.length <= 0)
                    return;

                LinkedList<File> lst = new LinkedList<File>();
                long totalSize = 0;

                for (File f : subs) {
                    lst.add(f);
                    totalSize += f.length();
                }

                //Log.d(TAG, "total size = " + totalSize);

                if (totalSize > MAX_TOTAL_FILE_SIZE) {
                    Log.d(TAG, "mName, too many logs. delete file to free space.");

                    Collections.sort(lst, FILE_CMP);

                    for (File f : lst) {
                        long size = f.length();

                        Log.d(TAG, mName + ", delete log file : " + f.getAbsolutePath());
                        if (!f.delete()) {
                            Log.e(TAG, mName + ", failed to delete log file : " + f.getAbsolutePath());
                        } else {
                            totalSize -= size;
                        }

                        if (totalSize < MAX_TOTAL_FILE_SIZE)
                            break;
                    }
                }
            } catch (Throwable e) {
                e.printStackTrace();
                Log.e(TAG, e.getMessage());
            }
        }

        protected void checkStorePath() throws IOException {
            if (mStoreInSdcard)
                return;

			/*
            if(Platform.getPlatform() == Platform.RK3368) {
				// do nothing
				// ...
				return;
			} else {
				if(!Storage.isSdcardMounted())
					return;
			}
			*/

            if (!android.os.Environment.getExternalStorageState().equals(
                    android.os.Environment.MEDIA_MOUNTED)) {
                Log.d(TAG, "SD UOT MEDIA_MOUNTED");
                return;
            }

//			if(!Storage.isSdcardMounted())
//				return;

            Log.d(TAG, mName + ", switch log file from data to sdcard");

            File fn = genSdcardLogFile();
            if (fn == null) {
                return;
            }

            // 关闭/data的日志文件
            closeLogFile();

            checkAndRemoveTotalLogFileSize(mStorePath);

            // 将/data的日志文件都移动到sdcard
            File[] subs = listStoredLogFile(TEMP_PATH);
            for (File f : subs) {
                File o = new File(mStorePath, f.getName());
                try {
                    copyFile(f, o);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // TODO : 复制失败，如何处理
                Log.d(TAG, mName + ", delete log file : " + f.getAbsolutePath());
                f.delete();
            }

            // 删除太旧的log文件(几天前)
            checkAndRemoveOldFile();

            openLogFile(fn);
        }

        protected File[] listStoredLogFile(File path) {
            return path.listFiles(new FileFilter() {
                private final String mPrefix = mName + FILENAME_SEPERATOR;

                @Override
                public boolean accept(File f) {
                    if (f.isDirectory())
                        return false;
                    if (f.getName().startsWith(mPrefix))
                        return true;
                    return false;
                }
            });
        }

        public void checkAndRemoveOldFile() {
            Log.d(TAG, mName + ", checkAndRemoveOldFile()");

            Date d = new Date();
            long delTime = d.getTime() - TIME_OF_FILE_TO_DELETE;

            checkAndRemoveOldFile(mStorePath, delTime);
            checkAndRemoveOldFile(TEMP_PATH, delTime);
        }

        private void checkAndRemoveOldFile(File path, long delTime) {
            File[] subs = listStoredLogFile(path);
            if (subs != null && subs.length != 0) {
                for (File f : subs) {
                    if (f.lastModified() < delTime) {
                        Log.d(TAG, mName + ", delete log file : " + f.getAbsolutePath());
                        if (!f.delete()) {
                            Log.e(TAG, mName + ", failed to delete log file : " + f.getAbsolutePath());
                        }
                    }
                }
            }
        }

        //protected File genLogFile(boolean sdcard) throws IOException {
        protected File genLogFile() throws IOException {
            String name = mName + FILENAME_SEPERATOR + DATE_FORMAT.format(new Date()) + LOGFILE_EXT;


            File fn = genSdcardLogFile();
            if (fn != null)
                return fn;
            return genTempLogFile(name);
        }

        protected File genSdcardLogFile() {
//            if (!Storage.isSdcardMounted()) {
//                return null;
//            }

            if (!android.os.Environment.getExternalStorageState().equals(
                    android.os.Environment.MEDIA_MOUNTED)) {
                Log.d(TAG, "SD UOT MEDIA_MOUNTED");
                return null;
            }

            if (!mStorePath.exists()) {
                if (!mStorePath.mkdirs()) {
                    Log.e(TAG, "failed to create dir : " + mStorePath);
                    return null;
                }
            }

            String name = mName + FILENAME_SEPERATOR + DATE_FORMAT.format(new Date()) + LOGFILE_EXT;
            File f = new File(mStorePath, name);
            try {
                if (f.createNewFile()) {
                    mStoreInSdcard = true;
                    return f;
                }
            } catch (IOException e) {
                //e.printStackTrace();
                Log.e(TAG, e.getMessage());
                Log.e(TAG, "failed to create log file. " + f.getAbsolutePath());
            }
            return null;
        }

        private File genTempLogFile(String name) throws IOException {
            mStoreInSdcard = false;
            if (!TEMP_PATH.exists()) {
                TEMP_PATH.mkdirs();
            }
            File f = new File(TEMP_PATH, name);
            if (f.createNewFile())
                return f;

            throw new IOException("failed to create temp log file.");
        }

        protected abstract int read(byte[] buffer) throws IOException;

        protected abstract void close();
    }

    private static class KmsgSaver extends AbstractSaver {
        private FileInputStream mInputStream = null;
        private DatagramSocket mSocket = null;
        private final LogBroadcastServer mBroadcastServer;

        public KmsgSaver(LogBroadcastServer bs, File path) {
            super("kmsg", path);
            mBroadcastServer = bs;
        }

        private void open() throws FileNotFoundException {
            if (mInputStream == null) {
                mInputStream = new FileInputStream("/proc/kmsg");
            }
        }

        @Override
        protected int read(byte[] buffer) throws IOException {
            open();
            int size = mInputStream.read(buffer);
            if (size > 0) {
                mBroadcastServer.broadcast(LogBroadcastServer.MSG_KERNEL, buffer, size);
            }
            return size;
        }

        @Override
        protected void close() {
            if (mInputStream != null) {
                try {
                    mInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mInputStream = null;
            }
        }
    }

    private static class LogcatSaver extends AbstractSaver {

        private Process mProc = null;
        private final LogBroadcastServer mBroadcastServer;

        public LogcatSaver(LogBroadcastServer bs, File path) {
            super("logcat", path);
            mBroadcastServer = bs;
        }

        private void open() throws IOException {
            if (mProc != null)
                return;
            ProcessBuilder pb = new ProcessBuilder();
            pb.command("logcat", "-v", "time");
            pb.redirectErrorStream(true);
            mProc = pb.start();
        }

        @Override
        protected int read(byte[] buffer) throws IOException {
            open();
            return mProc.getInputStream().read(buffer);
        }

        @Override
        protected void close() {
            if (mProc == null)
                return;
            Process p = mProc;
            mProc = null;
            p.destroy();
        }

    }
}
