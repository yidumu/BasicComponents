package com.example.clientsample.client;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import com.example.clientsample.databinding.ActivityBookmangerBinding;
import com.example.servicesample.Book;
import com.example.servicesample.IBookManager;
import com.example.servicesample.IOnNewBookArrivedListener;

import java.util.List;

import androidx.annotation.Nullable;

public class BookMangerActivity extends Activity {
    private static final String TAG = "BookMangerActivity";
    private static final int MESSAGE_NEW_BOOK_ARRIVED = 1;
    private ActivityBookmangerBinding binding;
    private IBookManager mRemoteBookManager;

    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_NEW_BOOK_ARRIVED:
                    Book book = (Book) msg.obj;
                    Log.d(TAG, "handleMessage: receive a new book" + book.bookName);
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    };

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            IBookManager bookManager = IBookManager.Stub.asInterface(service);
            try {
                mRemoteBookManager = bookManager;
                service.linkToDeath(mDeathRecipient, 0);
                bookManager.registerListener(mOnNewBookArrivedListener);
                List<Book> list = bookManager.getBookList();
                Log.d(TAG, "onServiceConnected: query book list , list type " + list.getClass().getCanonicalName());
                Log.d(TAG, "onServiceConnected: query book list: " + list.toString());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected() called with: name = [" + name + "]");
        }
    };

    private IBinder.DeathRecipient mDeathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            Log.d(TAG, "binderDied() called");
            if (mRemoteBookManager != null) {
                mRemoteBookManager.asBinder().unlinkToDeath(mDeathRecipient, 0);
                mRemoteBookManager = null;
                bindService();
            }
        }
    };

    private IOnNewBookArrivedListener mOnNewBookArrivedListener = new IOnNewBookArrivedListener.Stub() {
        @Override
        public void OnNewBookArrived(Book book) {
            mHandler.obtainMessage(MESSAGE_NEW_BOOK_ARRIVED, book).sendToTarget();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBookmangerBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        initView();
    }

    public void initView() {
        binding.bindservice.setOnClickListener(v -> {
            Log.d(TAG, "initView() called");
            bindService();
        });
        binding.addbook.setOnClickListener(v -> {
            addBook();
        });
    }

    public void bindService() {
        Intent intent = new Intent("");
        intent.setComponent(new ComponentName("com.example.servicesample", "com.example.servicesample.service.BookMangerService"));
        //intent.setAction("com.example.servicesample.service.BookMangerService");
        //intent.setPackage("com.example.servicesample");
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    public void addBook() {
        Book newBook = new Book(3, "Android开发艺术");
        if (mRemoteBookManager != null) {
            try {
                mRemoteBookManager.addBook(newBook);
                List<Book> newList = mRemoteBookManager.getBookList();
                Log.i(TAG, "onServiceConnected: query book list: " + newList.toString());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onDestroy() {
        if (mRemoteBookManager != null && mRemoteBookManager.asBinder().isBinderAlive()) {
            try {
                mRemoteBookManager.unregisterListener(mOnNewBookArrivedListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        unbindService(mConnection);
        super.onDestroy();
    }
}
