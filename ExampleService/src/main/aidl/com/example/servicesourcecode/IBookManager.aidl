// IBookManager.aidl
package com.example.servicesourcecode;

// Declare any non-default types here with import statements
import com.example.servicesourcecode.Book;
import com.example.servicesourcecode.IOnNewBookArrivedListener;

interface IBookManager {
    List<Book> getBookList();
    void addBook(in Book book);
    void registerListener(IOnNewBookArrivedListener listener);
    void unregisterListener(IOnNewBookArrivedListener listener);
}
