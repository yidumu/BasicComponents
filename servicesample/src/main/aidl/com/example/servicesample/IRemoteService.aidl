// IRemoteService.aidl
package com.example.servicesample;

// Declare any non-default types here with import statements

interface IRemoteService {
    /** Rect parcelable is stored in the bundle with key "rect" */
    oneway void saveRect(in Bundle bundle);

    Bundle setInPid(in Bundle bundle);
    Bundle setOutPid(out Bundle bundle);
    Bundle setInoutPid(inout Bundle bundle);
    int getPid();

    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
}