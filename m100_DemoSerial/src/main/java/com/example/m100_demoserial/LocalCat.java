package com.example.m100_demoserial;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;
import android.provider.ContactsContract.DeletedContacts;
import android.text.TextUtils;

public class LocalCat {

    private static LocalCat instance = null;
    private String dirPath;
    private int appid;
    private Thread logThread;

    public static LocalCat getInstance(Context context, String strpath)
    {
        if(instance == null)
            instance = new LocalCat(context, strpath);
        return instance;
    }

    public LocalCat(Context context, String strpath)
    {
        appid = android.os.Process.myPid();
        if(TextUtils.isEmpty(strpath)){
            dirPath = Environment.getExternalStorageDirectory().getAbsolutePath()
                    +File.separator+"seeker"+File.separator+context.getPackageName();
        }else{
            dirPath = strpath;
        }
        File dir = new File(dirPath);
        if(!dir.exists()){
            dir.mkdirs();
        }
    }

    public void start()
    {
        if(logThread == null){
            logThread = new Thread(new LogRunnable(appid, dirPath));
        }
        logThread.start();
    }

    /**
     2      * 删除指定目录下文件及目录
     3      * @param deleteThisPath
     4      * @param filepath
     5      * @return
     6      */
    public static void deleteFolderFile(String filePath, boolean deleteThisPath) {
        if (!TextUtils.isEmpty(filePath)) {
            try {
                File file = new File(filePath);
                if (file.isDirectory()) {// 处理目录
                    File files[] = file.listFiles();
                    for (int i = 0; i < files.length; i++) {
                        deleteFolderFile(files[i].getAbsolutePath(), true);
                    }
                }
                if (deleteThisPath) {
                    if (!file.isDirectory()) {// 如果是文件，删除
                        file.delete();
                    } else {// 目录
                        if (file.listFiles().length == 0) {// 目录下没有文件或者目录，删除
                            file.delete();
                        }
                    }
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private static class LogRunnable implements Runnable{
        private Process mProcess;
        private FileOutputStream fos;
        private BufferedReader mReader;
        private String cmds;
        private String mPid;

        public LogRunnable(int pid,String dirPath) {
            this.mPid = ""+pid;
            try {
                File file = new File(dirPath,FormatDate.getFormatDate()+".log");
                if(!file.exists()){
                    //新建文件之前，需要把以前的日志清除，因为存储空间有限，只能保持一天的日志
                    deleteFolderFile(dirPath, false);
                    file.createNewFile();
                }
                fos = new FileOutputStream(file,true);
            } catch (Exception e) {
                e.printStackTrace();
            }
            cmds = "logcat *:i | grep \"(" + mPid + ")\"";
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            try {
                mProcess = Runtime.getRuntime().exec(cmds);
                mReader = new BufferedReader(new InputStreamReader(mProcess.getInputStream()),1024);
                String line;
                while((line = mReader.readLine()) != null){
                    if(line.length() == 0){
                        continue;
                    }
                    if(fos != null && line.contains(mPid)){
                        //System.out.println((FormatDate.getFormatTime()+" "+line+"\r\n").getBytes());
                        fos.write((FormatDate.getFormatTime()+" "+line+"\r\n").getBytes());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                if(mProcess != null){
                    mProcess.destroy();
                    mProcess = null;
                }
                try {
                    if(mReader != null){
                        mReader.close();
                        mReader = null;
                    }
                    if(fos != null){
                        fos.close();
                        fos = null;
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }


    @SuppressLint("SimpleDateFormat")
    public static class FormatDate{

        public static String getFormatDate(){
            //SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
            return sdf.format(System.currentTimeMillis());
        }

        public static String getFormatTime(){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
            return sdf.format(System.currentTimeMillis());
        }
    }
}
