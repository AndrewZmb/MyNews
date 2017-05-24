package com.it.hr.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.it.hr.mynews.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangmingbo6 on 2017/4/16.
 */

/**
 *  Fragmet需要添加xml布局文件
 */


public class HomePageFragment extends Fragment {
    //首页标题导航tab
    private TabLayout tl_tabs_title;
    //首页内容ViewPager
    private ViewPager vp_index_content;

    @Nullable
    @Override  // 每个Fragment都有自己的XML配置文件
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("hrtest",this.getClass() + "---->2: onCreateView");
        //return x.view().inject(this,inflater,null);
        View view = View.inflate(this.getActivity(), R.layout.home_page,null);
        initControls(view);
        return view;
    }

    /**
     * 将首页标题导航TabLayout 与内容ViewPager关联
     * @param view
     */
    private  void initControls(View view){
        view.isInEditMode();
        tl_tabs_title =(TabLayout)view.findViewById(R.id.tl_tabs_title);
        vp_index_content =(ViewPager)view.findViewById(R.id.vp_index_content);

        //适配器
        vp_index_content.setAdapter(new ContenFragmentPage(getActivity().getSupportFragmentManager()));
        //首页标题导航TabLayout 与内容ViewPager关联
        tl_tabs_title.setupWithViewPager(vp_index_content);
    }

    /**
     *  Fragment适配器
     */
    private class ContenFragmentPage extends FragmentPagerAdapter {
        //标题
        String [] titles = new String[]{"要闻","直播","V观","下拉刷新"};
        //首页内容Fragment集合
        List<Fragment> iList=new ArrayList<Fragment>();

        //构造方法初始Fragment
        public ContenFragmentPage(FragmentManager fm) {
            super(fm);
            iList.add(new IndexContentPageFragment1());
            iList.add(new IndexContentPageFragment2());
            iList.add(new IndexContentPageFragment3());
            iList.add(new RefreshPageFragment());
        }

        @Override
        public Fragment getItem(int position) {
            return iList.get(position);
        }

        @Override
        public int getCount() {
            return iList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            //返回标题
            return titles[position];
        }
    }
}
