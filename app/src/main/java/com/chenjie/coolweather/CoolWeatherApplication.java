package com.chenjie.coolweather;

import android.app.Application;
import android.content.Context;

import org.litepal.LitePalApplication;

/**
 * Created by chenjie on 2017/7/15.
 */

public class CoolWeatherApplication extends Application {

    private static CoolWeatherApplication sharedApplication;
    private static Context sharedApplicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sharedApplicationContext = getApplicationContext(); //和this是同一对象
        sharedApplication = this;
        LitePalApplication.initialize(this);
    }

    public static CoolWeatherApplication getSharedApplication() {
        return sharedApplication;
    }

    public static Context getSharedApplicationContext() {
        return sharedApplicationContext;
    }
}
