package com.example.servicesourcecode.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;

import com.example.servicesourcecode.Book;
import com.example.servicesourcecode.IBookManager;
import com.example.servicesourcecode.IOnNewBookArrivedListener;
import com.example.servicesourcecode.R;
import com.example.servicesourcecode.service.BookMangerService;

import java.util.List;

import androidx.annotation.Nullable;

public class BookMangerActivity extends Activity {
    private static final String TAG = "BookMangerActivity";
    private static final int MESSAGE_NEW_BOOK_ARRIVED = 1;
    private IBookManager mRemoteBookManager;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_NEW_BOOK_ARRIVED:
                    Log.d(TAG, "handleMessage: recevie new book" + msg.obj);
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
                List<Book> list = bookManager.getBookList();
                Log.i(TAG, "onServiceConnected: query book list , list type " +
                        list.getClass().getCanonicalName());
                Log.i(TAG, "onServiceConnected: query book list :" + list.toString());
                Book newbook = new Book(3, "Android开发艺术");
                bookManager.addBook(newbook);
                List<Book> newlist = bookManager.getBookList();
                Log.i(TAG, "onServiceConnected: query book list :" + newlist.toString());
                bookManager.registerListener(mOnNewBookArrivedListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private IBinder.DeathRecipient mDeathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            Log.d(TAG, "binderDied: DeathRecipient");
            if (mRemoteBookManager != null) {
                mRemoteBookManager.asBinder().unlinkToDeath(mDeathRecipient, 0);
                mRemoteBookManager = null;
                Intent intent = new Intent(BookMangerActivity.this, BookMangerService.class);
                bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
            }
        }
    };
    private IOnNewBookArrivedListener mOnNewBookArrivedListener = new IOnNewBookArrivedListener.Stub() {
        @Override
        public void OnNewBookArrived(Book book) throws RemoteException {
            mHandler.obtainMessage(MESSAGE_NEW_BOOK_ARRIVED, book).sendToTarget();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, BookMangerService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
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
