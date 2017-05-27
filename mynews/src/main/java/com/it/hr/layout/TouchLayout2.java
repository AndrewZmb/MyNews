package com.it.hr.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by zhangmingbo6 on 2017/5/26.
 */

public class TouchLayout2 extends RelativeLayout {
    public TouchLayout2(Context context) {
        super(context);
    }

    public TouchLayout2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchLayout2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i("hrtest", this.getTag() + ":dispatchTouchEvent,EventType:" + ev);
        return super.dispatchTouchEvent(ev);
    }
    @Override
     public boolean onInterceptTouchEvent(MotionEvent ev) {
         Log.i("hrtest", this.getTag() + ":onInterceptTouchEvent,EventType:" + ev);
         return super.onInterceptTouchEvent(ev);
         }
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.i("hrtest", this.getTag() + ":onTouchEvent,EventType:" + ev);
        return super.onTouchEvent(ev);
         }
}
