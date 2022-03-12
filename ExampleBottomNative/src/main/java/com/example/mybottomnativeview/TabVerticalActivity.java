package com.example.mybottomnativeview;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mybottomnativeview.View.YViewPager;
import com.example.mybottomnativeview.fragment.DisplayFragment;
import com.example.mybottomnativeview.widget.QTabView;
import com.example.mybottomnativeview.widget.TabView;
import com.example.mybottomnativeview.widget.VerticalTabLayout;

import java.util.ArrayList;
import java.util.List;


public class TabVerticalActivity extends AppCompatActivity {

    private VerticalTabLayout tabLayout;
    private YViewPager mViewPager;
    private List<String> mTitle;
    private List<Fragment> mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_vertical);
        initData();
        initView();
    }

    private void initData() {
        mTitle = new ArrayList<>();
        mTitle.add("Tab0");
        mTitle.add("Tab1");
        mTitle.add("Tab3");
        mTitle.add("Tab4");

        mFragment = new ArrayList<>();
        mFragment.add(new OneFragment());
        mFragment.add(new twoFragment());
        mFragment.add(new ThreeFragment());
        mFragment.add(new DisplayFragment());
    }

    public void initView() {
        mViewPager = findViewById(R.id.tabviewpage);
        tabLayout = findViewById(R.id.tablayout);
        tabLayout.addTab(new QTabView(getApplicationContext()));
        tabLayout.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabView tab, int position) {

            }

            @Override
            public void onTabReselected(TabView tab, int position) {

            }
        });

        //预加载
        //mViewPager.setOffscreenPageLimit(mFragment.size());
        //mViewPager滑动监听
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        //设置适配器
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            //选中的item
            @Override
            public Fragment getItem(int i) {
                return mFragment.get(i);
            }

            //返回item的个数
            @Override
            public int getCount() {
                return mFragment.size();
            }

            //设置标题
            @Override
            public CharSequence getPageTitle(int position) {
                return mTitle.get(position);
            }
        });
        //绑定
        tabLayout.setupWithViewPager(mViewPager);
    }

}
