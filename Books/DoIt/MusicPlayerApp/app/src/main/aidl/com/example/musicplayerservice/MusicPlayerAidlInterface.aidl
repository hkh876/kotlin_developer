// MusicPlayerAidlInterface.aidl
package com.example.musicplayerservice;

// Declare any non-default types here with import statements

interface MusicPlayerAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    int getMaxDuration();
    void start();
    void stop();
}