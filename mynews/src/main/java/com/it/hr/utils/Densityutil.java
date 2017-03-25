package com.it.hr.utils;

import android.content.Context;

/**
 * Created by zhangmingbo6 on 2017/3/25.
 */

public class DensityUtil {

    public static  int dpi2px(Context context,int dpi){
        return (int)(context.getApplicationContext().getResources().getDisplayMetrics().density* dpi);
    }
    public static  int px2dpi(Context context,int px){
        return (int)(px/context.getApplicationContext().getResources().getDisplayMetrics().density);
    }
}
