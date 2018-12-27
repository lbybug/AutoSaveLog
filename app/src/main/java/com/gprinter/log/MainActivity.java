package com.gprinter.log;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.gprinter.log.service.LogServices;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    private EditText etLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
    }

    private void initData() {
        findViewById(R.id.btn_start).setOnClickListener(this);
        etLog = (EditText) findViewById(R.id.et_log);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                Log.e(TAG, "开启日志功能.");
//                new Thread(new Runnable() {
//                    int a = 1;
//
//                    @Override
//                    public void run() {
//                        while (true) {
//                            Log.e(TAG, "a= " + a);
//                            try {
//                                Thread.sleep(2000);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                            a++;
//                        }
//                    }
//                }).start();

                this.startService(new Intent(this, LogServices.class));

//                LogSaver.startSaveLog();
//                new LogcatServer().startLogcat();
                break;
            default:
                break;
        }
    }

    private static final int UPDATE_LOG_CODE = 0x100;

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_LOG_CODE:
                    String message = (String) msg.obj;
                    etLog.append(message);
                    break;
                default:
                    break;
            }
        }
    };

    public class LogcatServer implements Runnable {

        private boolean hasStart;

        @Override
        public void run() {

            try {
                Process process;
//                File rootUser = new File("/system/xbin/ru");
//                if (rootUser.exists()) {
//                    process = Runtime.getRuntime().exec(rootUser.getAbsolutePath());
//                } else {
//                    process = Runtime.getRuntime().exec("su");
//                }
//                process.getOutputStream().write("logcat -v time".getBytes());
//                process = Runtime.getRuntime().exec("cat /proc/kmsg");
                process = Runtime.getRuntime().exec("logcat -v time");
                InputStream inputStream = process.getInputStream();
                File file = new File("mnt/sdcard/myKmsg.txt");
                if (!file.exists()) {
                    boolean newFile = file.createNewFile();
                }
                OutputStream os = new FileOutputStream(file);
//                InputStreamReader isr = new InputStreamReader(inputStream);
//                BufferedReader br = new BufferedReader(isr);
//                String str = null;
//                while ((str = br.readLine()) != null) {
//
//

//                    mHandler.obtainMessage(UPDATE_LOG_CODE, str).sendToTarget();
//                }
//                mHandler.obtainMessage(UPDATE_LOG_CODE, "end ========================").sendToTarget();
                byte[] bytes = new byte[1024];
                int len = 0;
                while ((len = inputStream.read(bytes)) > 0) {
                    os.write(bytes, 0, len);
                }
                Log.e(TAG, "已完成...");
            } catch (IOException e) {
                e.printStackTrace();
//                mHandler.obtainMessage(UPDATE_LOG_CODE, ("Error: " + e.toString())).sendToTarget();
                Log.e(TAG, "出错了：" + e.toString());
                e.printStackTrace();
            }
        }

        public void startLogcat() {
            if (hasStart)
                return;

            new Thread(this).start();
            hasStart = true;
        }
    }

    /**
     * android 6.0 以上需要动态申请权限
     */
    private void initPermission() {
        String permissions[] = new String[0];
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            permissions = new String[]{Manifest.permission.INTERNET,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.READ_LOGS,
                    Manifest.permission.READ_CALL_LOG,
                    Manifest.permission.WRITE_CALL_LOG,
            };
        }

        ArrayList<String> toApplyList = new ArrayList<String>();

        for (String perm : permissions) {
            if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this, perm)) {
                toApplyList.add(perm);
                //进入到这里代表没有权限.
            }
        }
        String tmpList[] = new String[toApplyList.size()];
        if (!toApplyList.isEmpty()) {
            ActivityCompat.requestPermissions(this, toApplyList.toArray(tmpList), 123);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
