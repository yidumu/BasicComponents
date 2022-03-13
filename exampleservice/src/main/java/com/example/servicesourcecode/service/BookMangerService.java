package com.example.servicesourcecode.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;

import com.example.servicesourcecode.IBookManager;
import com.example.servicesourcecode.Book;
import com.example.servicesourcecode.IOnNewBookArrivedListener;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class BookMangerService extends Service {

    private static final String TAG = "BookMangerService";

    private AtomicBoolean mIsServiceDestoryed = new AtomicBoolean(false);
    private CopyOnWriteArrayList<Book> mBookList = new CopyOnWriteArrayList<>();
    //private CopyOnWriteArrayList<IOnNewBookArrivedListener> mListenerList = new CopyOnWriteArrayList<>();
    private RemoteCallbackList<IOnNewBookArrivedListener> mListenerList = new RemoteCallbackList<IOnNewBookArrivedListener>();

    private Binder mBinder = new IBookManager.Stub() {
        @Override
        public List<Book> getBookList() throws RemoteException {
            SystemClock.sleep(5000);
            return mBookList;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            mBookList.add(book);
        }

        @Override
        public void registerListener(IOnNewBookArrivedListener listener) throws RemoteException {
            /*if (!mListenerList.contains(listener)) {
                mListenerList.add(listener);
            } else {
                Log.d(TAG, "registerListener: already exists");
            }
            Log.d(TAG, "registerListener: size " + mListenerList.size());*/
            mListenerList.register(listener);
        }

        @Override
        public void unregisterListener(IOnNewBookArrivedListener listener) throws RemoteException {
          /*  if (mListenerList.contains(listener)) {
                mListenerList.remove(listener);
                Log.d(TAG, "unregisterListener: unregister listener succeed");
            } else {
                Log.d(TAG, "unregisterListener: no found , can not unregister");
            }
            Log.i(TAG, "unregisterListener: current size " + mListenerList.size());*/
            mListenerList.unregister(listener);
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        return mBinder;
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate");
        super.onCreate();
        mBookList.add(new Book(1, "Android"));
        mBookList.add(new Book(2, "IOS"));
        new Thread(new ServiceWorker()).start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        mIsServiceDestoryed.set(true);
        super.onDestroy();
    }

    private class ServiceWorker implements Runnable {

        @Override
        public void run() {
            while (!mIsServiceDestoryed.get()) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int bookId = mBookList.size() + 1;
                Book newBook = new Book(bookId, "newBook" + bookId);
                try {
                    onNewBookArrived(newBook);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                if (bookId == 10) {
                    //结束当前进程，测试Binder死亡回调
                    android.os.Process.killProcess(android.os.Process.myPid());
                    return;
                }
            }
        }
    }

    private void onNewBookArrived(Book book) throws RemoteException {
        mBookList.add(book);
        /*Log.d(TAG, "onNewBookArrived: notify listeners " + mListenerList.size());
        for (int i = 0; i < mListenerList.size(); i++) {
            IOnNewBookArrivedListener iOnNewBookArrivedListener = mListenerList.get(i);
            iOnNewBookArrivedListener.OnNewBookArrived(book);
        }*/
        final int N = mListenerList.beginBroadcast();
        for (int i = 0; i < N; i++) {
            IOnNewBookArrivedListener l = mListenerList.getBroadcastItem(i);
            if (l != null) {
                l.OnNewBookArrived(book);
            }
        }
        mListenerList.finishBroadcast();
    }
}
