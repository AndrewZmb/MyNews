package com.it.hr.mynews;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.it.hr.utils.SharePreUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.x;

import java.util.Timer;
import java.util.TimerTask;

@ContentView(R.layout.activity_index)
public class IndexActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_index);
        x.view().inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 此处可以判断用户是否开启网络, 或者启动Service下载网络数据,
        //两秒后打开引导页或主页
        new Timer(true).schedule(new TimerTask() {
            @Override
            public void run() {
                // 默认跳转到欢迎页面,如果欢迎页面已显示,则直接跳转主页面
                Boolean showWelcome =  SharePreUtil.getBoolean(IndexActivity.this,"show_welcome",true);
                if(showWelcome){
                    Intent intent = new Intent(IndexActivity.this,WelcomeActivity.class);
                    // 标准模式在同一个APP中所有Activity都在同一个栈
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } else {
                    startActivity( new Intent(IndexActivity.this,MainActivity.class));
                }
            }
        },2000);
    }
}
