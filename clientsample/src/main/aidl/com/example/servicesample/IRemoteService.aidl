// IRemoteService.aidl
package com.example.servicesample;

// Declare any non-default types here with import statements

interface IRemoteService {
    /** Rect parcelable is stored in the bundle with key "rect" */
    oneway void saveRect(in Bundle bundle);

    int setInPid(in int pid);
    int setOutPid(out int pid);
    int setInoutPid(inout int pid);

    int getPid();

    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
}