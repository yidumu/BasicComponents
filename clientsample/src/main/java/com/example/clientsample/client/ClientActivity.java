package com.example.clientsample.client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import com.example.clientsample.adapter.CommandAdapter;
import com.example.clientsample.databinding.ActivityClientBinding;
import com.example.servicesample.IRemoteService;
import com.example.servicesample.Rect;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

public class ClientActivity extends AppCompatActivity {
    private static final String TAG = "ClientActivity";

    private ActivityClientBinding binding;

    private IRemoteService remoteService;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected() called with: name = [" + name + "], service = [" + service + "]");
            remoteService = IRemoteService.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected() called with: name = [" + name + "]");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityClientBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        initView();
    }

    private void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
        binding.rvActionList.setLayoutManager(linearLayoutManager);
        binding.rvActionList.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        List<String> list = new ArrayList<>();
        list.add("binder");
        list.add("unBinder");
        list.add("setInPid");
        list.add("setOutPid");
        list.add("setInoutPid");
        list.add("saveRect");
        CommandAdapter commandAdapter = new CommandAdapter(list);
        binding.rvActionList.setAdapter(commandAdapter);
        commandAdapter.setOnClickListener(position -> {
            String name = list.get(position);
            Log.d(TAG, "initView() called : " + name);
            if (name.equals("binder")) {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("com.example.servicesample", "com.example.servicesample.service.RemoteService"));
                bindService(intent, connection, Context.BIND_AUTO_CREATE);
            } else if (name.equals("unBinder")) {
                unbindService(connection);
            } else if (name.equals("setInPid")) {
                try {
                    Bundle bundle = new Bundle();
                    bundle.putInt("KEY", Process.myPid());
                    Bundle remoteBundle = remoteService.setInPid(bundle);
                    int pid = remoteBundle.getInt("KEY");
                    Log.d(TAG, "initView() called pid : " + pid);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            } else if (name.equals("setOutPid")) {
                Bundle bundle = new Bundle();
                bundle.putInt("KEY", Process.myPid());
                try {
                    Bundle remoteBundle = remoteService.setOutPid(bundle);
                    int pid = remoteBundle.getInt("KEY");
                    Log.d(TAG, "initView() called pid : " + pid);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            } else if (name.equals("setInoutPid")) {
                Bundle bundle = new Bundle();
                bundle.putInt("KEY", Process.myPid());
                try {
                    Bundle remoteBundle = remoteService.setInoutPid(bundle);
                    int pid = remoteBundle.getInt("KEY");
                    Log.d(TAG, "initView() called pid : " + pid);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            } else if (name.equals("saveRect")) {
                Bundle bundle = new Bundle();
                Rect rect = new Rect();
                rect.top = 1;
                rect.right = 1;
                rect.left = 1;
                rect.bottom = 1;
                bundle.putParcelable("RECT", rect);
                try {
                    remoteService.saveRect(bundle);
                    Log.d(TAG, "initView() called saveRect");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}