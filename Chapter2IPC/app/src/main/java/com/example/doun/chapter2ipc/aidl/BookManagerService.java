//package com.example.doun.chapter2ipc.aidl;
package com.example.doun.chapter2ipc.aidl;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import com.example.doun.chapter2ipc.manualbinder.*;

public class BookManagerService extends Service {

    private static final String TAG = "BMS";

    private AtomicBoolean mIsServiceDestoryed = new AtomicBoolean(false);

    private CopyOnWriteArrayList<com.example.doun.chapter2ipc.manualbinder.Book> mBookList = new CopyOnWriteArrayList<com.example.doun.chapter2ipc.manualbinder.Book>();
    // private CopyOnWriteArrayList<com.example.doun.chapter2ipc.manualbinder.IOnNewBookArrivedListener> mListenerList =
    // new CopyOnWriteArrayList<com.example.doun.chapter2ipc.manualbinder.IOnNewBookArrivedListener>();

    private RemoteCallbackList<com.example.doun.chapter2ipc.manualbinder.IOnNewBookArrivedListener> mListenerList =
            new RemoteCallbackList<>();//RemoteCallbackList专门用于删除跨进程listener的接口

    private Binder mBinder = new com.example.doun.chapter2ipc.manualbinder.BookManagerImpl() {

        @Override
        public List<com.example.doun.chapter2ipc.manualbinder.Book> getBookList() throws RemoteException {
            SystemClock.sleep(5000);
            return mBookList;
        }

        @Override
        public void addBook(com.example.doun.chapter2ipc.manualbinder.Book book) throws RemoteException {
            mBookList.add(book);
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags)
                throws RemoteException {
            //验证permission
            int check = checkCallingOrSelfPermission("com.example.doun.chapter2ipc.permission.ACCESS_BOOK_SERVICE");
            Log.d(TAG, "check=" + check);
            if (check == PackageManager.PERMISSION_DENIED) {
                return false;
            }

            //验证包名
            String packageName = null;
            String[] packages = getPackageManager().getPackagesForUid(getCallingUid());
            if (packages != null && packages.length > 0) {
                packageName = packages[0];
            }
            Log.d(TAG, "onTransact: " + packageName);
            if (!packageName.startsWith("com.example")) {
                return false;
            }

            return super.onTransact(code, data, reply, flags);
        }

        @Override
        public void registerListener(com.example.doun.chapter2ipc.manualbinder.IOnNewBookArrivedListener listener)
                throws RemoteException {
            mListenerList.register(listener);

            final int N = mListenerList.beginBroadcast();//beginBroadcast和finishBroadcast必须配对使用
            mListenerList.finishBroadcast();
            Log.d(TAG, "registerListener, current size:" + N);
        }

        @Override
        public void unregisterListener(com.example.doun.chapter2ipc.manualbinder.IOnNewBookArrivedListener listener)
                throws RemoteException {
            boolean success = mListenerList.unregister(listener);

            if (success) {
                Log.d(TAG, "unregister success.");
            } else {
                Log.d(TAG, "not found, can not unregister.");
            }
            final int N = mListenerList.beginBroadcast();
            mListenerList.finishBroadcast();
            Log.d(TAG, "unregisterListener, current size:" + N);
        };

    };

    @Override
    public void onCreate() {
        super.onCreate();
        mBookList.add(new com.example.doun.chapter2ipc.manualbinder.Book(1, "Android"));
        mBookList.add(new com.example.doun.chapter2ipc.manualbinder.Book(2, "Ios"));
        new Thread(new ServiceWorker()).start();
    }

    @Override
    public IBinder onBind(Intent intent) {
        int check = checkCallingOrSelfPermission("com.example.doun.chapter2ipc.permission.ACCESS_BOOK_SERVICE");
        Log.d(TAG, "onbind check=" + check);
        if (check == PackageManager.PERMISSION_DENIED) {
            return null;
        }
        return mBinder;
    }

    @Override
    public void onDestroy() {
        mIsServiceDestoryed.set(true);
        super.onDestroy();
    }

    private void onNewBookArrived(com.example.doun.chapter2ipc.manualbinder.Book book) throws RemoteException {
        mBookList.add(book);
        final int N = mListenerList.beginBroadcast();
        for (int i = 0; i < N; i++) {
            com.example.doun.chapter2ipc.manualbinder.IOnNewBookArrivedListener l = mListenerList.getBroadcastItem(i);
            if (l != null) {
                try {
                    l.onNewBookArrived(book);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
        Log.d(TAG, "onNewBookArrived Broadcast");

        mListenerList.finishBroadcast();
    }

    private class ServiceWorker implements Runnable {
        @Override
        public void run() {
            // do background processing here.....
            while (!mIsServiceDestoryed.get()) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int bookId = mBookList.size() + 1;
                com.example.doun.chapter2ipc.manualbinder.Book newBook = new com.example.doun.chapter2ipc.manualbinder.Book(bookId, "new book#" + bookId);
                try {
                    onNewBookArrived(newBook);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
