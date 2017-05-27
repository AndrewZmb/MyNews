package com.it.hr.utils;

import android.app.Application;
import android.util.Log;

import org.xutils.x;

import cn.jpush.android.api.JPushInterface;


/**
 * Created by zhangmingbo6 on 2017/4/7.
 */

public class NewsApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("hrtest","代表当前应用、单例模式、项目启动的时候执行................");
        x.Ext.init(this);
        x.Ext.setDebug(true);
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }
}
