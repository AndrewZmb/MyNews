package com.it.hr.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.it.hr.mynews.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.x;

/**
 * Created by zhangmingbo6 on 2017/4/16.
 */

/**
 *  Fragmet需要添加xml布局文件
 */

@ContentView(value = R.layout.index_content_1_page)
public class IndexContentPageFragment1 extends Fragment {


    @Nullable
    @Override  // 每个Fragment都有自己的XML配置文件
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("hrtest",this.getClass() + "---->2: onCreateView");
        return x.view().inject(this,inflater,null);
    }




}
