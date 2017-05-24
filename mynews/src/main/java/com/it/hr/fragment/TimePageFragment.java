package com.it.hr.fragment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.it.hr.model.News;
import com.it.hr.mynews.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * Created by zhangmingbo6 on 2017/4/16.
 */
/**
 *  Fragmet需要添加xml布局文件
 */
@ContentView(R.layout.time_page)
public class TimePageFragment extends Fragment {

    @ViewInject(R.id.listview)
    private ListView listview;//新闻listView
    @ViewInject(R.id.tv_now)
    private TextView tv_now;//当前日期

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i("hrtest",this.getClass() + "---->1: onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("hrtest",this.getClass() + "---->2: onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("hrtest",this.getClass() + "---->3: onCreateView");
        return x.view().inject(this,inflater,null);
    }

    @Override  // 布局创建完毕,一般此方法用来初始化数据
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("hrtest",this.getClass() + "---->4: onViewCreated");
        View headView =View.inflate(this.getActivity(),R.layout.time_list_item_head,null);
        headView.measure(0,0);
        headView.setPadding(0,-headView.getMeasuredHeight(),0,0);
        listview.addHeaderView(headView);
        //设置当前日期
        setCurrentTime();

        // 先发送http请求获取新闻集合
        RequestParams params = new RequestParams("http://hiwbs101083.jsp.jspee.com.cn/NewServlet");
        params.addQueryStringParameter("name", "xUtils");

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //解析返回的新闻
                final List<News> newsList =  new Gson().fromJson(result,new TypeToken<List<News>>() { }.getType());
                //适配器
                listview.setAdapter(new BaseAdapter() {
                    @Override
                    public int getCount() {
                        return newsList.size();
                    }

                    @Override
                    public Object getItem(int position) {
                        return newsList.get(position);
                    }

                    @Override
                    public long getItemId(int position) {
                        return position;
                    }

                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        View view = null;
                        if(convertView!=null){ // 从缓存中获取
                            view =convertView;
                        }else{
                            view = View.inflate(TimePageFragment.this.getActivity(), R.layout.time_list_item,null);
                        }
                        //新闻model
                        News news = newsList.get(position);
                        //时间
                        TextView tv_time = (TextView)view.findViewById(R.id.tv_time);
                        //标题
                        TextView tv_title = (TextView)view.findViewById(R.id.tv_title);
                        //图片
                        ImageView iv_image = (ImageView)view.findViewById(R.id.iv_image);
                        tv_time.setText(news.getTime());
                        tv_title.setText(news.getTitle());

                        // 设置加载图片的参数
                        ImageOptions options = new ImageOptions.Builder()
                                // 是否忽略GIF格式的图片
                                .setIgnoreGif(false)
                                // 图片缩放模式
                                .setImageScaleType(ImageView.ScaleType.FIT_CENTER)
                                // 下载中显示的图片
                                .setLoadingDrawableId(R.drawable.time_loading)
                                // 下载失败显示的图片
                                .setFailureDrawableId(R.drawable.time_fail)
                                // 得到ImageOptions对象
                                .build();
                        // 加载图片
                        x.image().bind(iv_image, news.getImgUrl(), options, new Callback.CommonCallback<Drawable>() {
                            @Override
                            public void onSuccess(Drawable arg0) {
                                Log.i("hrtest", "onSuccess........");
                            }
                            @Override
                            public void onFinished() {
                                Log.i("hrtest", "onFinished........");
                            }
                            @Override
                            public void onError(Throwable arg0, boolean arg1) {
                                Log.i("hrtest", "onError........");
                            }
                            @Override
                            public void onCancelled(Callback.CancelledException arg0) {
                                Log.i("hrtest", "onCancelled........");
                            }
                        });
                        return view;
                    }
                });
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
            }
            @Override
            public void onCancelled(CancelledException cex) {
                Toast.makeText(x.app(), "cancelled", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFinished() {
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("hrtest",this.getClass() + "---->5: onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("hrtest",this.getClass() + "---->6: onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("hrtest",this.getClass() + "---->7: onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("hrtest",this.getClass() + "---->8: onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("hrtest",this.getClass() + "---->9: onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("hrtest",this.getClass() + "---->10: onDestroy");
    }

    /**
     * 设置当前日期
     */
    private void setCurrentTime(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        String now = format.format(new Date());
        tv_now.setText(now);
    }
}
