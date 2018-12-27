package com.gprinter.log.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.gprinter.log.LogSaver;

/**
 * FileName: LogServices
 * Author: Greetty
 * Time: 2018/12/26 14:06
 * Desc: TODO
 */
public class LogServices extends Service {


    private static final String TAG = "LogServices";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        Log.e(TAG, "===onBind ===");
        Log.e(TAG, "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.e(TAG, "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        Log.e(TAG, "===onStartCommand ===");
        Log.e(TAG, "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");

        Log.e(TAG, "开启日志保存线程");
        LogSaver.startSaveLog();

        new Thread() {
            int a = 1;

            @Override
            public void run() {
                while (true) {
                    Log.e(TAG, "&&&&&&&&&&&  应用还存活，未被杀死" + a + "  &&&&&&&&&&&  aaa=== ");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    a++;
                }
            }
        }.start();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        Log.e(TAG, "===onDestroy ===");
        Log.e(TAG, "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
    }
}
