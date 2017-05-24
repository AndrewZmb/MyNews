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

@ContentView(value = R.layout.tv_page)
public class TvPageFragment extends Fragment {


    @Nullable
    @Override  // 每个Fragment都有自己的XML配置文件
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("jxy",this.getClass() + "---->2: onCreateView");
        return x.view().inject(this,inflater,null);
//        return View.inflate(this.getActivity(), R.layout.home_page,null);
    }




}
