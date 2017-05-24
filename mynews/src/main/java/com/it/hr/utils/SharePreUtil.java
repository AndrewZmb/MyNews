package com.it.hr.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by zhangmingbo6 on 2017/3/25.
 */

public class SharePreUtil {

    private final static   String config ="myNews";

    /**
     * 将Boolean 值写入myNews.xml文件
     * @param context 安卓上下文
     * @param key  键
     * @param value 值
     */
    public static void setBoolean(Context context,String key,Boolean value){
        SharedPreferences ref =  context.getSharedPreferences(config,Context.MODE_PRIVATE);
        ref.edit().putBoolean(key,value).commit();
    }

    /**
     * 从myNews.xml文件获取Boolean
     * @param context  安卓上下文
     * @param key 键
     * @param value 默认值
     * @return Boolean
     */
    public static Boolean getBoolean(Context context,String key,Boolean value){
        SharedPreferences ref =  context.getSharedPreferences(config,Context.MODE_PRIVATE);
        return ref.getBoolean(key,value);
    }
}
