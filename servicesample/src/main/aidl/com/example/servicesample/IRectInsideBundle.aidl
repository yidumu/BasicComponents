// IRectInsideBundle.aidl
package com.example.servicesample;

// Declare any non-default types here with import statements

interface IRectInsideBundle {
    /** Rect parcelable is stored in the bundle with key "rect" */
    void saveRect(in Bundle bundle);
}