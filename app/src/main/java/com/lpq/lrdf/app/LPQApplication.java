package com.lpq.lrdf.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;

import com.lpq.lrdf.config.AppConfig;
import com.lpq.lrdf.config.ISPKey;
import com.lpq.lrdf.di.components.ApiServerComponent;
import com.lpq.lrdf.di.components.DaggerApiServerComponent;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;
import com.lpq.base.lib.LibApplication;
import com.lpq.base.lib.activity.LibBaseActivity;
import com.lpq.lrdf.main.activity.MainActivity;

/**
 * 功能：LPQApplication
 * 增加获取Mac地址 和 App当前版本号 by:lipanquan  2018-1-9 <br />
 * 作者：lipanquan on 2018/1/9 <br />
 * 邮箱：862321807@qq.cn <br />
 */
public class LPQApplication extends LibApplication implements ISPKey {
    private static LPQApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        appVersionName = getVersionName();
        //TODO 上线前关闭
        CustomActivityOnCrash.install(this);  //崩溃日志
    }

    @Override
    protected boolean getIsDebug() {
        return AppConfig.IS_DEBUG;
    }

    @Override
    public void onTerminate() {
        // 程序终止的时候执行
        super.onTerminate();
    }

    /**
     * 获取LPQApplication
     *
     * @return LPQApplication
     */
    public static LPQApplication getAppContext() {
        return sInstance;
    }

    /**
     * ApiServerComponent对象
     */
    private static ApiServerComponent apiServerComponent;

    /**
     * 获取Application中的ApiServerComponent对象
     *
     * @return ApiServerComponent对象
     */
    public ApiServerComponent getApiServerComponent() {
        synchronized (this.getClass()) {
            if (apiServerComponent == null) {
                apiServerComponent = DaggerApiServerComponent.builder().build();
            }
            return apiServerComponent;
        }
    }

    /**
     * 当前app的版本
     */
    private String appVersionName;

    /**
     * 获取当前app的版本
     *
     * @return 当前app的版本
     */
    public String getAppVersionName() {
        if (appVersionName == null)
            appVersionName = getVersionName();
        return appVersionName;
    }

    /**
     * Mac地址
     */
    private String mac;

    /**
     * 获取Mac地址
     *
     * @return Mac地址
     */
    public String getMac() {
        if (mac == null || mac.length() < 1) {
            WifiManager wifiManager =
                    (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = (null == wifiManager ? null : wifiManager.getConnectionInfo());
            if (!wifiManager.isWifiEnabled()) {
                //必须先打开，才能获取到MAC地址
                wifiManager.setWifiEnabled(true);
                wifiManager.setWifiEnabled(false);
            }
            if (null != info)
                mac = info.getMacAddress();
        }
        return mac;
    }

    /**
     * 获取当前app的版本
     *
     * @return 当前app的版本
     */
    private String getVersionName() {
        try {
            return this.getPackageManager().getPackageInfo(
                    getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String deviceId = null;

    /**
     * 获取设备序列号
     *
     * @return
     */
    @SuppressLint("MissingPermission")
    public String getSerialNumber() {
        if (this.deviceId != null && !"".equals(this.deviceId))
            return this.deviceId;
//        return deviceId = ((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
        return deviceId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    /**
     * 获取MainActivity
     *
     * @return MainActivity
     */
    public MainActivity getMainActivity() {
        for (LibBaseActivity activity : this.activities) {
            if (activity instanceof MainActivity)
                return (MainActivity) activity;
        }
        return null;
    }
}