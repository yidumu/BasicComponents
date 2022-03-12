// IOnNewBookArrivedListener.aidl
package com.example.servicesourcecode;

// Declare any non-default types here with import statements
import com.example.servicesourcecode.Book;

interface IOnNewBookArrivedListener {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void OnNewBookArrived(in Book book);
}
