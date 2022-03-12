// IRectInsideBundle.aidl
package com.example.servicesourcecode;

// Declare any non-default types here with import statements

/** Example service interface */
interface IRectInsideBundle {
    /** Rect parcelable is stored in the bundle with key "rect" */
    void saveRect(in Bundle bundle);
}
