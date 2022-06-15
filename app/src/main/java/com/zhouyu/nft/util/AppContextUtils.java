package com.zhouyu.nft.util;

import android.Manifest;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

public class AppContextUtils {

    private static PackageManager manager;

    /**
     * 获取包管理类
     */
    public static PackageManager getPackageManager(Context context) {
        try {
            if (manager == null) {
                manager = context.getPackageManager();
            }
            return manager;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取包信息
     */
    public static synchronized PackageInfo getPackageInfo(Context context) {
        try {
            PackageManager manager = getPackageManager(context);
            if (manager == null) {
                return null;
            }
            return manager.getPackageInfo(getPackageName(context), PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException | NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 获取包名
     */
    private static String packageName;

    public static String getPackageName(Context context) {
        try {
            if (TextUtils.isEmpty(packageName)) {
                packageName = context.getPackageName();
            }
            return packageName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取设备号
     * @param context
     * @return
     */
    public static String getDeviceId(Context context) {
        String deviceId;

        if (Build.VERSION.SDK_INT >= 29) {
            deviceId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        } else {
            final TelephonyManager mTelephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (context.checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                    return "";
                }
            }
            assert mTelephony != null;
            if (mTelephony.getDeviceId() != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    deviceId = mTelephony.getImei();
                } else {
                    deviceId = mTelephony.getDeviceId();
                }
            } else {
                deviceId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
            }
        }
        return deviceId;
    }



    /**
     * 获取应用信息
     */
    public static ApplicationInfo getApplicationInfo(Context context) {
        try {
            PackageManager manager = getPackageManager(context);
            if (manager == null) {
                return null;
            }
            return manager.getApplicationInfo(getPackageName(context),
                    PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException | NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }

}
