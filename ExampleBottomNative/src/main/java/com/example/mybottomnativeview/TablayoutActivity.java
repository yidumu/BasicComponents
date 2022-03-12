package com.example.mybottomnativeview;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class TablayoutActivity extends AppCompatActivity {


    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private List<String> mTitle;
    private List<Fragment> mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);
        initData();
        initView();
    }

    private void initData(){
        mTitle=new ArrayList<>();
        mTitle.add("Tab1");
        mTitle.add("Tab2");
        mTitle.add("Tab3");

        mFragment=new ArrayList<>();
        mFragment.add(new OneFragment());
        mFragment.add(new twoFragment());
        mFragment.add(new ThreeFragment());
    }

    private void initView(){
        mTabLayout=findViewById(R.id.tab_vertical);
        mViewPager=findViewById(R.id.viewpage_vertical);
        //预加载
        mViewPager.setOffscreenPageLimit(mFragment.size());
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
        mViewPager.setPageTransformer(true,new VerticalPageTransfromer());
        //绑定
        mTabLayout.setupWithViewPager(mViewPager);
    }
}

