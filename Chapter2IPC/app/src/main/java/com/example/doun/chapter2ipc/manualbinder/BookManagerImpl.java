package com.example.doun.chapter2ipc.manualbinder;

import java.util.List;

import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public class BookManagerImpl extends Binder implements IBookManager {

    /**
     * Construct the stub at attach it to the interface.
     */
    public BookManagerImpl() {
        this.attachInterface(this, DESCRIPTOR);
    }

    /**
     * Cast an IBinder object into an IBookManager interface, generating a proxy
     * if needed.
     */
    public static IBookManager asInterface(IBinder obj) {
        if ((obj == null)) {
            return null;
        }
        android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
        if (((iin != null) && (iin instanceof IBookManager))) {
            return ((IBookManager) iin);
        }
        return new BookManagerImpl.Proxy(obj);
    }

    @Override
    public IBinder asBinder() {
        return this;
    }

    @Override
    public boolean onTransact(int code, Parcel data, Parcel reply, int flags)
            throws RemoteException {
        switch (code) {
            case INTERFACE_TRANSACTION: {
                reply.writeString(DESCRIPTOR);
                return true;
            }
            case TRANSACTION_getBookList: {
                data.enforceInterface(DESCRIPTOR);
                List<Book> result = this.getBookList();
                reply.writeNoException();
                reply.writeTypedList(result);
                return true;
            }
            case TRANSACTION_addBook: {
                data.enforceInterface(DESCRIPTOR);
                Book arg0;
                if ((0 != data.readInt())) {
                    arg0 = Book.CREATOR.createFromParcel(data);
                } else {
                    arg0 = null;
                }
                this.addBook(arg0);
                reply.writeNoException();
                return true;
            }

            case TRANSACTION_registerListener: {
                data.enforceInterface(DESCRIPTOR);
                com.example.doun.chapter2ipc.manualbinder.IOnNewBookArrivedListener _arg0;
                _arg0 = com.example.doun.chapter2ipc.manualbinder.OnNewBookArrivedListenerImpl.asInterface(data.readStrongBinder());
                this.registerListener(_arg0);
                reply.writeNoException();
                return true;
            }
            case TRANSACTION_unregisterListener: {
                data.enforceInterface(DESCRIPTOR);
                com.example.doun.chapter2ipc.manualbinder.IOnNewBookArrivedListener _arg0;
                _arg0 = com.example.doun.chapter2ipc.manualbinder.OnNewBookArrivedListenerImpl.asInterface(data.readStrongBinder());
                this.unregisterListener(_arg0);
                reply.writeNoException();
                return true;
            }
        }
        return super.onTransact(code, data, reply, flags);
    }

    @Override
    public List<Book> getBookList() throws RemoteException {
        // TODO 待实现
        return null;
    }

    @Override
    public void addBook(Book book) throws RemoteException {
        // TODO 待实现
    }

    @Override
    public void registerListener(IOnNewBookArrivedListener listener) throws RemoteException {

    }

    @Override
    public void unregisterListener(IOnNewBookArrivedListener listener) throws RemoteException {

    }

    private static class Proxy implements IBookManager {
        private IBinder mRemote;

        Proxy(IBinder remote) {
            mRemote = remote;
        }

        @Override
        public IBinder asBinder() {
            return mRemote;
        }

        public java.lang.String getInterfaceDescriptor() {
            return DESCRIPTOR;
        }

        @Override
        public List<Book> getBookList() throws RemoteException {
            Parcel data = Parcel.obtain();
            Parcel reply = Parcel.obtain();
            List<Book> result;
            try {
                data.writeInterfaceToken(DESCRIPTOR);
                mRemote.transact(TRANSACTION_getBookList, data, reply, 0);
                reply.readException();
                result = reply.createTypedArrayList(Book.CREATOR);
            } finally {
                reply.recycle();
                data.recycle();
            }
            return result;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            Parcel data = Parcel.obtain();
            Parcel reply = Parcel.obtain();
            try {
                data.writeInterfaceToken(DESCRIPTOR);
                if ((book != null)) {
                    data.writeInt(1);
                    book.writeToParcel(data, 0);
                } else {
                    data.writeInt(0);
                }
                mRemote.transact(TRANSACTION_addBook, data, reply, 0);
                reply.readException();
            } finally {
                reply.recycle();
                data.recycle();
            }
        }

        @Override
        public void registerListener(com.example.doun.chapter2ipc.manualbinder.IOnNewBookArrivedListener listener) throws android.os.RemoteException {
            android.os.Parcel _data = android.os.Parcel.obtain();
            android.os.Parcel _reply = android.os.Parcel.obtain();
            try {
                _data.writeInterfaceToken(DESCRIPTOR);
                _data.writeStrongBinder((((listener != null)) ? (listener.asBinder()) : (null)));
                mRemote.transact(TRANSACTION_registerListener, _data, _reply, 0);
                _reply.readException();
            } finally {
                _reply.recycle();
                _data.recycle();
            }
        }

        @Override
        public void unregisterListener(com.example.doun.chapter2ipc.manualbinder.IOnNewBookArrivedListener listener) throws android.os.RemoteException {
            android.os.Parcel _data = android.os.Parcel.obtain();
            android.os.Parcel _reply = android.os.Parcel.obtain();
            try {
                _data.writeInterfaceToken(DESCRIPTOR);
                _data.writeStrongBinder((((listener != null)) ? (listener.asBinder()) : (null)));
                mRemote.transact(TRANSACTION_unregisterListener, _data, _reply, 0);
                _reply.readException();
            } finally {
                _reply.recycle();
                _data.recycle();
            }
        }
    }


}
