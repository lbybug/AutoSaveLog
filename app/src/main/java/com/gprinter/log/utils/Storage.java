package com.gprinter.log.utils;

import java.io.File;
import java.lang.reflect.Method;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Environment;
import android.os.ServiceManager;
import android.os.storage.IMountService;
import android.os.storage.StorageVolume;
import android.util.Log;

public class Storage {

    private static final String TAG = "Storage";
    private static final int FLAG_FOR_WRITE = 1 << 0;

    private static File mExternalStorageDirectory = null;


    public static File getExternalStorageDirectory() {
        if (mExternalStorageDirectory == null) {
            try {
                if (Build.VERSION.SDK_INT >= 23) {
                    Log.e(TAG, "SDK_INT >= 23");
                    final IMountService mountService = IMountService.Stub.asInterface(
                            ServiceManager.getService("mount"));

                    Method m = IMountService.class.getDeclaredMethod("getVolumeList", int.class, String.class, int.class);
                    Object o = m.invoke(mountService, 0, null, FLAG_FOR_WRITE);
                    StorageVolume[] v = (StorageVolume[]) o;

                    Class<StorageVolume> c = StorageVolume.class;
                    Method method = c.getMethod("getPathFile");
                    method.setAccessible(true);
                    Object obj = method.invoke(v[0]);
                    mExternalStorageDirectory = (File) obj;

                    Log.e(TAG, "mExternalStorageDirectory: " + mExternalStorageDirectory.getPath());
                } else {
                    Log.e(TAG, "SDK_INT < 23");
                    mExternalStorageDirectory = Environment.getExternalStorageDirectory();
                    Log.e(TAG, "mExternalStorageDirectory: " + mExternalStorageDirectory.getPath());
                }
            } catch (Throwable e) {
                e.printStackTrace();
                Log.e(TAG, "get file error: " + e.getMessage());
            }
        }
        Log.e(TAG, " ======== end end  ========");
        return mExternalStorageDirectory;
    }

    @SuppressLint("NewApi")
    public static boolean isSdcardMounted() {

        if (Build.VERSION.SDK_INT >= 23) {
            if (mExternalStorageDirectory == null) {
                getExternalStorageDirectory();
            }
            return true;
        } else {
            String state = Environment.getExternalStorageState();
            return Environment.MEDIA_MOUNTED.equals(state);
        }
    }
}
