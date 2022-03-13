package com.example.servicesourcecode.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.servicesourcecode.R;

import androidx.appcompat.app.AppCompatActivity;

public class BroadcastActivity extends AppCompatActivity {
    private static final String TAG = "BroadcastActivity";
    private BroadcastReceiver broadcastReceiver;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);
        init();
        button = findViewById(R.id.btn_sendbroad);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("com.example.servicesourcecode.MY_BROADCAST");
                sendBroadcast(intent);
            }
        });
    }

    public void init() {
        broadcastReceiver = new MyBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        intentFilter.addAction("com.example.servicesourcecode.MY_BROADCAST");
        this.registerReceiver(broadcastReceiver, intentFilter);

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.unregisterReceiver(broadcastReceiver);
    }

    public class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            final PendingResult pendingResult = goAsync();
            Task asyncTask = new Task(pendingResult, intent);
            asyncTask.execute();
        }
    }

    private static class Task extends AsyncTask<String, Integer, String> {
        private final BroadcastReceiver.PendingResult pendingResult;
        private final Intent intent;

        private Task(BroadcastReceiver.PendingResult pendingResult, Intent intent) {
            this.pendingResult = pendingResult;
            this.intent = intent;
        }

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder sb = new StringBuilder();
            sb.append("Action: " + intent.getAction() + "\n");
            sb.append("URI: " + intent.toUri(Intent.URI_INTENT_SCHEME).toString() + "\n");
            String log = sb.toString();
            Log.d(TAG, log);
            return log;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            // Must call finish() so the BroadcastReceiver can be recycled.
            pendingResult.finish();
        }
    }
}
