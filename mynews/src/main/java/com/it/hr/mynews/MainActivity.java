package com.it.hr.mynews;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.RadioGroup;

import com.it.hr.fragment.FindPageFragment;
import com.it.hr.fragment.HomePageFragment;
import com.it.hr.fragment.MePageFragment;
import com.it.hr.fragment.TimePageFragment;
import com.it.hr.fragment.TvPageFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_main)
public class MainActivity extends FragmentActivity {
    @ViewInject(R.id.mainRg)
    private RadioGroup radioGroup;
    @ViewInject(R.id.main_vp)
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        x.view().inject(this);
        radioGroup.check(R.id.rbHome);
        //初始化数据
        initData();
    }

    private void initData() {
        // 如果添加Fragment则需要相应的适配器支持
        viewPager.setAdapter(new MainFragmentPage(getSupportFragmentManager()));
        // 给按钮组注册单击事件,让单击触发ViewPager页面切换
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbHome:
                        viewPager.setCurrentItem(0,true);
                        break;
                    case  R.id.rbTime:
                        viewPager.setCurrentItem(1,true);
                        break;
                    case  R.id.rbTv:
                        viewPager.setCurrentItem(2,true);
                        break;
                    case  R.id.rbFind:
                        viewPager.setCurrentItem(3,true);
                        break;
                    case  R.id.rbMe:
                        viewPager.setCurrentItem(4,true);
                        break;
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.exit(0);  // 退出app 0 代表正常退出
    }

    private class MainFragmentPage extends FragmentPagerAdapter{

        List<Fragment> iList=new ArrayList<Fragment>();

        public MainFragmentPage(FragmentManager fm) {
            super(fm);
            iList.add(new HomePageFragment());
            iList.add(new TimePageFragment());
            iList.add(new TvPageFragment());
            iList.add(new FindPageFragment());
            iList.add(new MePageFragment());
        }

        @Override
        public Fragment getItem(int position) {
            return iList.get(position);
        }

        @Override
        public int getCount() {
            return iList.size();
        }
    }
}
