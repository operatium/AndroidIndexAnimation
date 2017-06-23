package com.demo1.utils;

import android.app.ActivityManager;
import android.app.ActivityManager.*;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.jakewharton.disklrucache.DiskLruCache;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by java on 2017/5/22.
 */

public class AppUtils {

    //缓存
    public static DiskLruCache getCache(Context context)
    {
        DiskLruCache cache = null;
        try {
            cache = DiskLruCache.open(new File(context.getFilesDir().getAbsolutePath() + File.separator + "yyxcache"), 1, 1, 10 * 1024 * 1024);
        }catch (Exception e)
        {
            Log.e("201706131338",e.toString());
        }
        return cache;
    }

    public static void writeCache(Context context,String key, String str)
    {
        try{
            DiskLruCache diskLruCache = getCache(context);
            if (diskLruCache!=null)
            {
                DiskLruCache.Editor edit= diskLruCache.edit(key);
                OutputStream outputStream = edit.newOutputStream(0);
                outputStream.write(str.getBytes());
                outputStream.close();
                edit.commit();
                diskLruCache.flush();
                diskLruCache.close();
            }
            else
            {
                Log.e("201706131347","(缓存失败)"+"key = "+key+"      value = "+str);
            }
        }catch (Exception e)
        {
            Log.e("201706131346",e.toString());
        }
    }

    public static String readCache(Context context,String key)
    {
        String str="";
        try{
            DiskLruCache diskLruCache = getCache(context);
            if (diskLruCache!=null)
            {
                DiskLruCache.Snapshot snapshot = diskLruCache.get(key);
                if (snapshot != null) {
                    InputStream inputStream = snapshot.getInputStream(0);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    int i;
                    while ((i = inputStream.read()) != -1) {
                        baos.write(i);
                    }
                    str = baos.toString();
                    inputStream.close();
                    baos.close();
                }
                diskLruCache.flush();
                diskLruCache.close();
            }
            else
            {
                Log.e("201706131410","(读取缓存失败)"+"key = "+key);
            }
        }catch (Exception e)
        {
            Log.e("201706131411",e.toString());
        }
        Log.i("show","(缓存)"+"key = "+key+"      value = "+str);
        return str;
    }

    //判断当前app是否在前台
    public static boolean isBackground(Context context) {
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningAppProcessInfo> appProcesses = activityManager
                .getRunningAppProcesses();
        for (RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(context.getPackageName())) {
                /*
                BACKGROUND=400 EMPTY=500 FOREGROUND=100
                GONE=1000 PERCEPTIBLE=130 SERVICE=300 ISIBLE=200
                 */
                Log.i(context.getPackageName(), "此appimportace ="
                        + appProcess.importance
                        + ",context.getClass().getName()="
                        + context.getClass().getName());
                if (appProcess.importance != RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    Log.i(context.getPackageName(), "处于后台"
                            + appProcess.processName);
                    return true;
                } else {
                    Log.i(context.getPackageName(), "处于前台"
                            + appProcess.processName);
                    return false;
                }
            }
        }
        return false;
    }

    //CUP核数
    public static int getNumberOfCPUCores() {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.GINGERBREAD_MR1) {
            return 1;
        }
        int cores;
        try {
            cores = new File("/sys/devices/system/cpu/").listFiles(CPU_FILTER).length;
        } catch (Exception e) {
            cores = -1;
        }
        Log.e("cpu",cores+"");
        return cores;
    }

    private static final FileFilter CPU_FILTER = new FileFilter() {
        @Override
        public boolean accept(File pathname) {
            String path = pathname.getName();
            if (path.startsWith("cpu")) {
                for (int i = 3; i < path.length(); i++) {
                    if (path.charAt(i) < '0' || path.charAt(i) > '9') {
                        return false;
                    }
                }
                return true;
            }
            return false;
        }
    };
}
