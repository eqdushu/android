package com.tmacbo.eqdushu.common;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.util.Log;
import cn.jpush.android.api.JPushInterface;
import cn.share.jack.cyghttp.app.CygApplication;
import cn.share.jack.cyghttp.app.HttpServletAddress;
import com.tmacbo.eqdushu.BuildConfig;

/**
 * Created by jack on 2017/6/13
 */

public class App extends CygApplication {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        CommonConfig.init(this);
        //设置模式
        CommonConfig.setDebug(BuildConfig.DEBUG);
        //Fresco.initialize(this);
        JPushInterface.setDebugMode(BuildConfig.DEBUG);
        JPushInterface.init(this);
        Log.d("JPUSH", JPushInterface.getUdid(this));
        CommonConfig.PUSH_ID = JPushInterface.getRegistrationID(this);
        HttpServletAddress.getInstance()
                          .setOfflineAddress(CommonConfig.getBASE_URL());
    }
}
