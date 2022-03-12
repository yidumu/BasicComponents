package com.example.mybottomnativeview.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mybottomnativeview.R;
import com.example.mybottomnativeview.View.ToggleButton;

public class Luminance_DFragment extends Fragment {

    private View view;
    private TextView textView;
    private String flag;
    ToggleButton toggleButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //flag=getArguments().getString("Flag");
        Bundle bundle = getArguments();
        if (bundle!=null) {
            flag = bundle.getString("flag");
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_version_information, container, false);
            textView = view.findViewById(R.id.tv_version_title);
            toggleButton=view.findViewById(R.id.tb_fragmen_switch);
            toggleButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    textView.setVisibility(View.GONE);
                }
            });
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (flag != null) {
            textView.setText(flag);
        }
    }

    @Override
    public void onDestroy() {
        textView.setVisibility(View.GONE);
        super.onDestroy();

    }
}
