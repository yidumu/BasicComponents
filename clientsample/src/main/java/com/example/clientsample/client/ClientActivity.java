package com.example.clientsample.client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import com.example.clientsample.R;
import com.example.clientsample.databinding.ActivityClientBinding;
import com.example.servicesample.IRemoteService;

import androidx.appcompat.app.AppCompatActivity;

public class ClientActivity extends AppCompatActivity {
    private static final String TAG = "ClientActivity";

    private ActivityClientBinding binding;

    private IRemoteService remoteService;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected() called with: name = [" + name + "], service = [" + service + "]");
            remoteService = IRemoteService.Stub.asInterface(service);
            try {
                Log.d(TAG, "onServiceConnected: " + remoteService.getPid());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
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
        binding.binder.setOnClickListener(v -> {
                    Log.d(TAG, "onCreate: ");
                    Intent intent = new Intent();
                    intent.setComponent(new ComponentName("com.example.servicesample", "com.example.servicesample.service.RemoteService"));
                    bindService(intent, connection, Context.BIND_AUTO_CREATE);
                }
        );
        binding.unbinder.setOnClickListener(v -> {
            Log.d(TAG, "onCreate: ");
            unbindService(connection);
        });
    }
}