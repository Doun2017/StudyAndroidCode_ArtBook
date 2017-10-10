package com.example.doun.chapter2ipc.aidl;

import com.example.doun.chapter2ipc.aidl.Book;
import com.example.doun.chapter2ipc.aidl.IOnNewBookArrivedListener;

interface IBookManager {
     List<Book> getBookList();
     void addBook(in Book book);
     void registerListener(IOnNewBookArrivedListener listener);
     void unregisterListener(IOnNewBookArrivedListener listener);
}