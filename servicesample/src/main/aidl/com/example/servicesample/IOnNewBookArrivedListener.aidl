// IOnNewBookArrivedListener.aidl
package com.example.servicesample;

// Declare any non-default types here with import statements
import com.example.servicesample.Book;

interface IOnNewBookArrivedListener {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void OnNewBookArrived(in Book book);
}
