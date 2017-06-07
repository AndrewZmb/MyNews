package com.it.hr.mynews;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.it.hr.utils.SharePreUtil;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.circlenavigator.CircleNavigator;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;
@ContentView(R.layout.activity_welcome)
public class WelcomeActivity extends Activity {

    //引导切换view
    @ViewInject(R.id.vp_image)
    private ViewPager viewPager;

    //modify by andrew
    //引导页里的小圆点线型布局
    //@ViewInject(R.id.ll)
    //private LinearLayout ll =null;

    //小红点移动的距离
    private int pointMoveWidth ;
    //开始体验按钮
    private Button button;

    private List<View> iList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_welcome);
        x.view().inject(this);
        // 绑定组件
        initView();
        //初始化数据
        //initData();
    }

    private void initView() {
        final MagicIndicator magicIndicator = (MagicIndicator) findViewById(R.id.magic_indicator);
        final CircleNavigator circleNavigator = new CircleNavigator(this);

        //给ViewPager 适配item(一般是ImageView)
        viewPager.setAdapter(new WelcomePage() );
        /**  modify by andrew
         //添加三个小圆点
         for(int i=0;i<iList.size();i++){
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
        });  **/

        //注册一个监听页面滑动事件
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.i("hrtest", "当前页面的索引：" + position+",移动距离百分比："+positionOffset+",当前页面滑动像素:"+positionOffsetPixels);
                /** modify by andrew
                 View redPoint  = findViewById(R.id.red_point);
                 RelativeLayout.LayoutParams params = ( RelativeLayout.LayoutParams)redPoint.getLayoutParams();
                 params.leftMargin = (int)((pointMoveWidth * positionOffset) + position *pointMoveWidth) ;
                 redPoint.setLayoutParams(params);
                 **/
                magicIndicator.onPageScrolled(position, positionOffset, positionOffsetPixels);
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

        circleNavigator.setCircleCount(iList.size());
        circleNavigator.setCircleColor(Color.RED);
        magicIndicator.setNavigator(circleNavigator);

    }

//    private void initData() {
//
//    }


    private class WelcomePage extends PagerAdapter {
        private int[] ids = null;
        public WelcomePage() {
            iList = new ArrayList<View>();
            ids = new int[]{R.drawable.guide_1, R.drawable.guide_2, R.drawable.guide_3};
            //创建一个list<ImageView>用来存储图片
            for (int id : ids) {
                ImageView imageView = new ImageView(WelcomeActivity.this);
                imageView.setBackgroundResource(id);
                iList.add(imageView);
            }
            iList.add(View.inflate(WelcomeActivity.this,R.layout.btn_start,null));
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
            container.removeView((View)object);
        }

        @Override //实例每一个item ，其实就是一个view
        public Object instantiateItem(ViewGroup container, int position) {
            View view = (View)iList.get(position);
            Log.i("hrtest","当前的ViewPager对象："+container+",position:"+position+",view:"+view);
            //返回imageView 须将imageView 放到container
            container.addView(view);// lv.addView(view); 只能使用适配器
            return view;
        }
    }
    public  void startMainActivity(View view){
        //设置欢迎页面已经显示过一次
        SharePreUtil.setBoolean(this,"show_welcome",false);
        //打开主页
        Intent intent = new Intent(this,MainActivity.class);
        //标准模式同一个APP中所有的ACTIVITY 都在同一个栈
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        // 启动主页面
        startActivity(intent);


    }
}
