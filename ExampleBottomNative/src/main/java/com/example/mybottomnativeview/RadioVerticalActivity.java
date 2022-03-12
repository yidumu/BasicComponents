package com.example.mybottomnativeview;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.mybottomnativeview.adapter.baseAdapter;
import com.example.mybottomnativeview.fragment.FragmentManagerHelper;
import com.example.mybottomnativeview.fragment.Luminance_DFragment;
import com.example.mybottomnativeview.fragment.baseViewFragment;
import com.example.mybottomnativeview.pattern.observer.DynamicView;
import com.example.mybottomnativeview.pattern.observer.FunctionList;
import com.example.mybottomnativeview.pattern.observer.ObserverSubject;

import java.util.ArrayList;
import java.util.List;

public class RadioVerticalActivity extends AppCompatActivity {

    private static final String TAG =RadioVerticalActivity.class.getSimpleName();

    private RecyclerView recyclerView;;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<String> arrayList = new ArrayList<>();
    private baseAdapter mBaseAdapter;
    private static int CurrentPage = 0;
    private FragmentManagerHelper mFragmentManagerHelper;

    //第一栏
    private RadioGroup mRadioGroup;
    private RadioButton rbConect;
    private RadioButton rbDisplay;
    private RadioButton rbSound;
    private RadioButton rbSystem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_vertical);
        initFindId();
        addListData();
        setRecycleView();
        //rbConect.setChecked(true);
        mFragmentManagerHelper= new FragmentManagerHelper(getSupportFragmentManager(),R.id.fl_module_windows);
        initObserver();
    }

    public void  initObserver(){
        ObserverSubject observerSubject = new ObserverSubject();
        observerSubject.attach(new DynamicView());
        observerSubject.attach(new FunctionList());
        observerSubject.setState(1);

    }

    public void initFindId(){
        mRadioGroup = findViewById(R.id.rg_setting);
        rbConect = findViewById(R.id.rb_connect);
        rbConect.setChecked(true);
        rbDisplay = findViewById(R.id.rb_display);
        rbSystem = findViewById(R.id.rb_system);
        rbSound = findViewById(R.id.rb_sound);

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_connect:
                        CurrentPage = 0;
                        if(rbConect.isChecked()){
                            //更改RecycleView数据
                            List<String> list = new ArrayList<>();
                            list.add("abc");
                            list.add("bcd");
                            mBaseAdapter.notifyData(list);
                        }
                        break;
                    case R.id.rb_display:
                        CurrentPage = 1;
                        List<String> list = new ArrayList<>();
                        String[] datas = getResources().getStringArray(R.array.list_setting_display);
                        for(String data : datas){
                            list.add(data);
                        }
                        mBaseAdapter.notifyData(list);
                        break;
                    case R.id.rb_sound:
                        CurrentPage=2;
                        List<String> list1 = new ArrayList<>();
                        list1.add("abc");
                        list1.add("abc");
                        list1.add("abc");

                        mBaseAdapter.notifyData(list1);
                        break;
                    case R.id.rb_system:
                        CurrentPage = 3;
                        List<String> list3 = new ArrayList<>();
                        list3.add("vvv");
                        list3.add("vvv");
                        list3.add("vvv");
                        list3.add("vvv");
                        mBaseAdapter.notifyData(list3);
                        //mRadioGroup.clearCheck();
                        break;
                }
            }
        });
    }

    public void addListData(){
        for(int i=0;i<20;i++) {
            arrayList.add("number : " + i);
        }
    }

    public void setRecycleView() {
        recyclerView = findViewById(R.id.rv_list);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // specify an adapter (see also next example)
        mBaseAdapter = new baseAdapter(arrayList,this);
        mBaseAdapter.setOnitemClickLintener(new baseAdapter.OnitemClick() {
            @Override
            public void onItemClick(int position) {
                Log.d(TAG,"onItemClick " + position );
                //switchFragment(position);
                mFragmentManagerHelper.switchFragment(new OneFragment().getClass());
            }
        });
        mBaseAdapter.setOnLongClickListener(new baseAdapter.OnLongClick() {
            @Override
            public void onLongClick(int position) {
                Log.d(TAG,"onLongClick " + position );

            }
        });

        mBaseAdapter.setonRefreshFragment(new baseAdapter.onRefreshFragment() {
            @Override
            public void refresh(int item) {
                switchFragment(item);
            }
        });
        recyclerView.setAdapter(mBaseAdapter);
        switchFragment(0);
    }

    public void switchFragment(int id){
            getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_module_windows,getCorrespondence(id))
                .commit();
    }

    public Fragment getCorrespondence(int id) {
        Fragment fragment = new Luminance_DFragment();
        Bundle bundle =new Bundle();
        bundle.putString("flag",String.valueOf(id)+"--"+CurrentPage );
        fragment.setArguments(bundle);
        return fragment;
    }
}
