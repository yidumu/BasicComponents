package com.example.mybottomnativeview.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mybottomnativeview.OneFragment;
import com.example.mybottomnativeview.R;
import com.example.mybottomnativeview.twoFragment;
import com.example.mybottomnativeview.adapter.MyAdapter;

import java.util.ArrayList;
import java.util.List;

public class DisplayFragment extends Fragment {

    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private View view;
    private Activity activity;
    private Context context;

    @Override
    public void onAttach(Context context) {
        this.activity = (Activity) context;
        this.context = context;
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_display, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRecycleView();
        //getChildFragmentManager().beginTransaction().add(R.id.fl_show,new OneFragment()).commit();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.add(R.id.fl_show, new OneFragment());
        // Commit the transaction
        transaction.commit();
    }

    public void setRecycleView() {
        recyclerView = view.findViewById(R.id.my_recycler_view);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        // specify an adapter (see also next example)
        String myDataset[] = {"语言", "亮度", "字体", "模式"};
        List<String> list = new ArrayList<>();
        mAdapter = new MyAdapter(list);
        mAdapter.setOnitemClickLintener(new MyAdapter.OnitemClick() {
            @Override
            public void onItemClick(int position) {

            }
        });
        mAdapter.setOnLongClickListener(new MyAdapter.OnLongClick() {
            @Override
            public void onLongClick(int position) {
                switchFragment(position+1);
            }
        });
        recyclerView.setAdapter(mAdapter);
    }

    public void switchFragment(int id){
        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_show,getCorrespondence(id))
                .commit();
    }

    public Fragment getCorrespondence(int id) {
        switch (id) {
            case 1:
                return new OneFragment();
            case 2:
                return new twoFragment();
            case 3:
                return new Luminance_DFragment();
        } ;
        return new OneFragment();
    }

    /*Fragmen中嵌套Fragment popupwindow+recyclerview实现
    FragmentTransaction ft = getChildFragmentManager().beginTransaction();
    List<Fragment> fragments = getChildFragmentManager().getFragments();
    for (Fragment fragment : fragments) {
    ft.remove(fragment);}
    *ft.commit();*/
}