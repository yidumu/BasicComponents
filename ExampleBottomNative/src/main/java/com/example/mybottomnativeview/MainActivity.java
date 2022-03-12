package com.example.mybottomnativeview;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.mybottomnativeview.adapter.baseFragmentPageAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    //private TextView mTextMessage;
    private ViewPager mviewPager;
    private FragmentManager mfragmentManager;
    private List<Fragment> fragmentList;
    MenuItem menuItem;
    BottomNavigationView navigation;


    private BottomNavigationView.OnNavigationItemSelectedListener monNavigationItemSelectedListener=new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    mviewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_dashboard:
                    //mTextMessage.setText(R.string.title_dashboard);
                    mviewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_notifications:
                    //mTextMessage.setText(R.string.title_notifications);
                    mviewPager.setCurrentItem(2);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //mTextMessage=findViewById(R.id.messagetextview);
        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(monNavigationItemSelectedListener);
        mviewPager=findViewById(R.id.pageview);
        mfragmentManager=getSupportFragmentManager();
        fragmentList=new ArrayList<>();
        fragmentList.add(new OneFragment());
        fragmentList.add(new twoFragment());
        fragmentList.add(new ThreeFragment());
        baseFragmentPageAdapter fragmentPageAdapter=new baseFragmentPageAdapter(mfragmentManager,fragmentList);
        mviewPager.setAdapter(fragmentPageAdapter);
        mviewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onPageSelected(int i) {
        if(menuItem!=null){
            menuItem.setChecked(false);

        }else{
            menuItem=navigation.getMenu().getItem(0);
            menuItem.setChecked(true);
        }
        menuItem=navigation.getMenu().getItem(i);
        menuItem.setChecked(true);
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
}
