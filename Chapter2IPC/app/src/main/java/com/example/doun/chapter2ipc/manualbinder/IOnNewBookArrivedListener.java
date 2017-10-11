/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: H:\\StudyAndroidCode_ArtBook\\Chapter2IPC\\app\\src\\main\\aidl\\com\\example\\doun\\chapter2ipc\\aidl\\IOnNewBookArrivedListener.aidl
 */
package com.example.doun.chapter2ipc.manualbinder;

public interface IOnNewBookArrivedListener extends android.os.IInterface {
    static final java.lang.String DESCRIPTOR = "com.example.doun.chapter2ipc.aidl.IOnNewBookArrivedListener";

    static final int TRANSACTION_onNewBookArrived = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);


    public void onNewBookArrived(com.example.doun.chapter2ipc.manualbinder.Book newBook) throws android.os.RemoteException;
}
