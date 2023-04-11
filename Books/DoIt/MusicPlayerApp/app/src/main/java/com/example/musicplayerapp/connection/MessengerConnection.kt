package com.example.musicplayerapp.connection

import android.content.ComponentName
import android.content.ServiceConnection
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import android.util.Log

class MessengerConnection : ServiceConnection {
    lateinit var messenger: Messenger
    lateinit var replyMessenger: Messenger

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        Log.d(MESSENGER_CONNECTION_TAG, "onServiceConnected...")

        messenger = Messenger(service)

        val msg = Message()
        msg.replyTo = replyMessenger
        msg.what = MESSENGER_CONNECTION_INIT_CODE
        messenger.send(msg)
    }

    override fun onServiceDisconnected(name: ComponentName?) {
        Log.d(MESSENGER_CONNECTION_TAG, "onServiceDisconnected...")
    }

    companion object {
        const val MESSENGER_CONNECTION_TAG = "MessengerConnection"
        const val MESSENGER_CONNECTION_INIT_CODE = 10
        const val MESSENGER_CONNECTION_STOP_CODE = 20
    }
}