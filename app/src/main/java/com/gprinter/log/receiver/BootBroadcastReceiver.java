package com.gprinter.log.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.gprinter.log.service.LogServices;

/**
 * FileName: BootBroadcastReceiver
 * Author: Administrators
 * Time: 2018/12/26 9:21
 * Desc: 开机完成广播
 */
public class BootBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "BootBroadcastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.e(TAG, "================================================");
        Log.e(TAG, "================================================");
        Log.e(TAG, "=========      收到开机广播       ========");
        Log.e(TAG, "================================================");
        Log.e(TAG, "================================================");
        //收到开机广播后，开始日志保存功能
        //LogSaver.startSaveLog();


        Log.e(TAG, "启动服务，。。。。。");
        context.startService(new Intent(context, LogServices.class));
    }

}
