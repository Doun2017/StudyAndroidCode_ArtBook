/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: H:\\StudyAndroidCode_ArtBook\\Chapter2IPC\\app\\src\\main\\aidl\\com\\example\\doun\\chapter2ipc\\aidl\\IOnNewBookArrivedListener.aidl
 */
package com.example.doun.chapter2ipc.manualbinder;

import android.os.RemoteException;

public class OnNewBookArrivedListenerImpl extends android.os.Binder implements com.example.doun.chapter2ipc.manualbinder.IOnNewBookArrivedListener {
    @Override
    public void onNewBookArrived(com.example.doun.chapter2ipc.manualbinder.Book newBook) throws RemoteException {
        // TODO 待实现

    }

    /**
     * Construct the stub at attach it to the interface.
     */
    public OnNewBookArrivedListenerImpl() {
        this.attachInterface(this, DESCRIPTOR);
    }

    /**
     * Cast an IBinder object into an com.example.doun.chapter2ipc.aidl.IOnNewBookArrivedListener interface,
     * generating a proxy if needed.
     */
    public static com.example.doun.chapter2ipc.manualbinder.IOnNewBookArrivedListener asInterface(android.os.IBinder obj) {
        if ((obj == null)) {
            return null;
        }
        android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
        if (((iin != null) && (iin instanceof com.example.doun.chapter2ipc.manualbinder.IOnNewBookArrivedListener))) {
            return ((com.example.doun.chapter2ipc.manualbinder.IOnNewBookArrivedListener) iin);
        }
        return new com.example.doun.chapter2ipc.manualbinder.OnNewBookArrivedListenerImpl.Proxy(obj);
    }

    @Override
    public android.os.IBinder asBinder() {
        return this;
    }

    @Override
    public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException {
        switch (code) {
            case INTERFACE_TRANSACTION: {
                reply.writeString(DESCRIPTOR);
                return true;
            }
            case TRANSACTION_onNewBookArrived: {
                data.enforceInterface(DESCRIPTOR);
                com.example.doun.chapter2ipc.manualbinder.Book _arg0;
                if ((0 != data.readInt())) {
                    _arg0 = com.example.doun.chapter2ipc.manualbinder.Book.CREATOR.createFromParcel(data);
                } else {
                    _arg0 = null;
                }
                this.onNewBookArrived(_arg0);
                reply.writeNoException();
                return true;
            }
        }
        return super.onTransact(code, data, reply, flags);
    }

    private static class Proxy implements com.example.doun.chapter2ipc.manualbinder.IOnNewBookArrivedListener {
        private android.os.IBinder mRemote;

        Proxy(android.os.IBinder remote) {
            mRemote = remote;
        }

        @Override
        public android.os.IBinder asBinder() {
            return mRemote;
        }

        public java.lang.String getInterfaceDescriptor() {
            return DESCRIPTOR;
        }

        @Override
        public void onNewBookArrived(com.example.doun.chapter2ipc.manualbinder.Book newBook) throws android.os.RemoteException {
            android.os.Parcel _data = android.os.Parcel.obtain();
            android.os.Parcel _reply = android.os.Parcel.obtain();
            try {
                _data.writeInterfaceToken(DESCRIPTOR);
                if ((newBook != null)) {
                    _data.writeInt(1);
                    newBook.writeToParcel(_data, 0);
                } else {
                    _data.writeInt(0);
                }
                mRemote.transact(OnNewBookArrivedListenerImpl.TRANSACTION_onNewBookArrived, _data, _reply, 0);
                _reply.readException();
            } finally {
                _reply.recycle();
                _data.recycle();
            }
        }
    }

}
