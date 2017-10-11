package com.example.doun.chapter2ipc.manualbinder;

import java.util.List;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;

public interface IBookManager extends IInterface {

    static final String DESCRIPTOR = "com.ryg.chapter_2.manualbinder.IBookManager";

    static final int TRANSACTION_getBookList = (IBinder.FIRST_CALL_TRANSACTION + 0);
    static final int TRANSACTION_addBook = (IBinder.FIRST_CALL_TRANSACTION + 1);
    static final int TRANSACTION_registerListener = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
    static final int TRANSACTION_unregisterListener = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);

    public List<Book> getBookList() throws RemoteException;

    public void addBook(Book book) throws RemoteException;

    public void registerListener(com.example.doun.chapter2ipc.manualbinder.IOnNewBookArrivedListener listener) throws android.os.RemoteException;
    public void unregisterListener(com.example.doun.chapter2ipc.manualbinder.IOnNewBookArrivedListener listener) throws android.os.RemoteException;

}
