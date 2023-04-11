package com.example.musicplayerapp.connection

import android.content.ComponentName
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import com.example.musicplayerservice.MusicPlayerAidlInterface

class AIDLConnection : ServiceConnection {
    lateinit var aidlService: MusicPlayerAidlInterface

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        Log.d(AIDL_CONNECTION_TAG, "onServiceConnected...")
        aidlService = MusicPlayerAidlInterface.Stub.asInterface(service)
        aidlService.start()
    }

    override fun onServiceDisconnected(name: ComponentName?) {
        Log.d(AIDL_CONNECTION_TAG, "onServiceDisconnected...")
    }

    companion object {
        const val AIDL_CONNECTION_TAG = "AIDLConnection"
    }
}