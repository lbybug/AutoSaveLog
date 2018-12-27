package com.gprinter.log;

import android.util.Log;

import com.gprinter.log.utils.Storage;

/**
 * FileName: Test
 * Author: Greetty
 * Time: 2018/12/27 13:40
 * Desc: TODO
 */
public class Test {

    private static final String TAG = "Test";

    public static void main(String[] args) {

//        Storage storage = new Storage();

        Storage.getExternalStorageDirectory();

        new Thread() {
            int count = 1;
            @Override
            public void run() {
                while (true) {

                    Log.e(TAG, "====================================");
                    Log.e(TAG, "==============count=" + count + "==========");
                    Log.e(TAG, "====================================");
                    System.out.println("====================================");
                    System.out.println("==============count=" + count + "==========");
                    System.out.println("====================================");
                    count++;
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

    }

}
