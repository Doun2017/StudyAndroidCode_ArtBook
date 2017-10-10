package com.example.doun.chapter2ipc.aidl;

import com.example.doun.chapter2ipc.aidl.Book;

interface IOnNewBookArrivedListener {
    void onNewBookArrived(in Book newBook);
}
