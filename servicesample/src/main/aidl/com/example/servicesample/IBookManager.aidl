// IBookManager.aidl
package com.example.servicesample;

// Declare any non-default types here with import statements
import com.example.servicesample.Book;
import com.example.servicesample.IOnNewBookArrivedListener;

interface IBookManager {
    List<Book> getBookList();
    void addBook(in Book book);
    void registerListener(IOnNewBookArrivedListener listener);
    void unregisterListener(IOnNewBookArrivedListener listener);
}
