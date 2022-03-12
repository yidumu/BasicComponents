package com.example.mybottomnativeview.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mybottomnativeview.R;

public class baseViewFragment extends Fragment {

    private View view;
    private TextView textView;
    private LinearLayout linearLayout;
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
        view = inflater.inflate(R.layout.fragment_version_information, container, false);
        linearLayout = view.findViewById(R.id.ll_fragment_base);
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        /*TextView textView = new TextView(activity);
        textView.setText("增加");

       *//*   textView.setGravity();
        textView.setBackground(new ColorDrawable(Color.GREEN));
        ViewGroup.LayoutParams layoutParams = linearLayout.getLayoutParams();
        LinearLayout linearLayout = new LinearLayout(activity);
        linearLayout.setOrientation();*//*

        linearLayout.addView(textView);*/
    }
}
