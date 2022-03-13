package com.example.servicesourcecode.socket;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.NoRouteToHostException;
import java.net.Socket;
import java.net.SocketTimeoutException;


public class DataService extends Service {

    private final static String TAG = DataService.class.getSimpleName();
    private Socket TcpSocket;
    //private String Host="169.254.80.45";
    private String Host="192.168.245.7";
    private int Port=7654;

    /*初始化socket*/
    public void ConnectSocket() {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        TcpSocket = new Socket();
                        TcpSocket.connect(new InetSocketAddress(Host, Port), 2000);
                        if (TcpSocket.isConnected()) {
                            Log.i(TAG,"手机端Socket已连接");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        if (e instanceof SocketTimeoutException) {
                            Log.i(TAG,"连接超时，正在重连");
                        } else if (e instanceof NoRouteToHostException) {
                            Log.i(TAG,"该地址不存在，请检查");
                        } else if (e instanceof ConnectException) {
                            Log.i(TAG,"连接异常或被拒绝，请检查");
                        }
                    }
                }
            }).start();
    }

    //通过socket发送数据
    public void sendData(final String value){
        new Thread(new Runnable() {
            @Override
            public void run() {
                OutputStream out;
                try {
                    if (TcpSocket==null||TcpSocket.getOutputStream() == null){
                        return;
                    }
                    out = TcpSocket.getOutputStream();
                    DataOutputStream dos = new DataOutputStream(out);
                    // 步骤2：写入需要发送的数据到输出流对象中
                    dos.write(value.getBytes("utf-8"));
                    out.flush();
                    dos.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void ReceiveData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                //通过socket接收数据
                    byte[] bytes = new byte[1024];
                    int bLen=0;
                    try {
                        InputStream streamFromSocket = TcpSocket.getInputStream();
                        DataInputStream dataInputStream = new DataInputStream(streamFromSocket);
                        while(true) {
                            bLen=dataInputStream.read(bytes,0,1024);
                            Log.i("MyReceive", "bLen  length:" + bLen);
                            byte[] mconsequence = new byte[bLen];
                            for (int i=0;i<bLen;i++) {
                                mconsequence[i] = bytes[i];
                            }
                            Log.i(TAG,"接收服务器消息："+new String(mconsequence));
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        }).start();
    }


    private IBinder mBinder = new LocalBinder();

    class LocalBinder extends Binder {
        public DataService getService() {
            return DataService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }
}
