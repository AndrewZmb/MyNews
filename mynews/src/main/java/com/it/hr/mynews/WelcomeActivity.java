package com.it.hr.mynews;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.it.hr.utils.DensityUtil;

import java.util.ArrayList;
import java.util.List;

public class WelcomeActivity extends Activity {

    //引导切换view
    private ViewPager viewPager;
    //引导页里的小圆点线型布局
    private LinearLayout ll =null;
    //小红点移动的距离
    private int pointMoveWidth ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        // 绑定组件
        initView();
        //初始化数据
        initData();
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.vp_image);
        ll = (LinearLayout) findViewById(R.id.ll);
    }

    private void initData() {
        //给ViewPager 适配item(一般是ImageView)
        viewPager.setAdapter(new WelcomePage() );
        //添加三个小圆点
        for(int i=0;i<3;i++){
            View view = new View(this);
            view.setBackgroundResource(R.drawable.welcome_point_gray);
            //代码里的像素都是px
            LinearLayout.LayoutParams param =  new LinearLayout.LayoutParams(DensityUtil.dpi2px(this,10),DensityUtil.dpi2px(this,10));
            if(i!=0){
                param.leftMargin = 20;
            }
            view.setLayoutParams(param);
            ll.addView(view);
        }
        //通过对tree观察者监听，可以动态计算移动的距离
        ll.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener(){
            @Override
            public void onGlobalLayout() {
                pointMoveWidth = ll.getChildAt(1).getLeft() - ll.getChildAt(0).getLeft();
                ll.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });

        //注册一个监听页面滑动事件
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.i("hrtest", "当前页面的索引：" + position+",移动距离百分比："+positionOffset+",当前页面滑动像素:"+positionOffsetPixels);
                View redPoint  = findViewById(R.id.red_point);
                RelativeLayout.LayoutParams params = ( RelativeLayout.LayoutParams)redPoint.getLayoutParams();
                params.leftMargin = (int)((pointMoveWidth * positionOffset) + position *pointMoveWidth) ;
                redPoint.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int position) {
                Log.i("hrtest", "当前选择的页面:" + position);
            }

            @Override //0：什么都没做 1：开始滑动 2：滑动结束
            public void onPageScrollStateChanged(int state) {
                Log.i("hrtest", "正在切换:" + state);
            }
        });
    }


    private class WelcomePage extends PagerAdapter {
        private int[] ids = null;
        private List<ImageView> iList;
        public WelcomePage() {
            iList = new ArrayList<ImageView>();
            ids = new int[]{R.drawable.guide_1, R.drawable.guide_2, R.drawable.guide_3};
            //创建一个list<ImageView>用来存储图片
            for (int id : ids) {
                ImageView imageView = new ImageView(WelcomeActivity.this);
                imageView.setBackgroundResource(id);
                iList.add(imageView);
            }
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public int getCount() {
            return iList.size();
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            Log.i("hrtest", "当前销毁的对象:" + object);
            container.removeView((ImageView)object);
        }

        @Override //实例每一个item ，其实就是一个view
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = iList.get(position);
            Log.i("hrtest","当前的ViewPager对象："+container+",position:"+position+",imageView:"+imageView);
            //返回imageView 须将imageView 放到container
            container.addView(imageView);// lv.addView(view); 只能使用适配器
            return imageView;
        }
    }
}
