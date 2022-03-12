package com.example.mybottomnativeview.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import java.util.List;

public class FragmentManagerHelper {
    private FragmentManager fragmentManager;
    private int containerViewId;

    public FragmentManagerHelper(FragmentManager fragmentManager, int containerViewId) {
        this.fragmentManager = fragmentManager;
        this.containerViewId = containerViewId;
    }

    public void switchFragment(Class<? extends Fragment> cls) {
        String TAG = cls.getSimpleName();
        Fragment target = fragmentManager.findFragmentByTag(TAG);
        if(target==null){
            try {
                target = cls.newInstance();
            }catch (Exception e){
                e.printStackTrace();
                return;
            }
        }else if(!target.isHidden()){
            return;
        }
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        List<Fragment> list = fragmentManager.getFragments();
        Log.d("TAG", "list" + list.size());
        for (Fragment fragments : list) {
            fragmentTransaction.hide(fragments);
        }
        if (list.contains(target)) {
            fragmentTransaction.show(target);
        } else {
            fragmentTransaction.add(containerViewId, target, TAG);
        }
        fragmentTransaction.commit();
    }

}
