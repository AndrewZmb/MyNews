package com.it.hr.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.it.hr.mynews.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by zhangmingbo6 on 2017/4/16.
 */

/**
 *  Fragmet需要添加xml布局文件
 */

@ContentView(value = R.layout.me_page)
public class MePageFragment extends Fragment {

    @ViewInject(R.id.user_wv)
    private BridgeWebView bridgeWebView;

    @Nullable
    @Override  // 每个Fragment都有自己的XML配置文件
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("hrtest", this.getClass() + "---->2: onCreateView");
        return x.view().inject(this, inflater, null);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        // 通过webview注册一个方法,此方法提供H5调用,获取远程数据之后json格式返回即可
        // 加载本地的H5页面
        bridgeWebView.loadUrl("file:///android_asset/user_center.html");
    }
}