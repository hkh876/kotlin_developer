package com.example.musicplayerapp.handler

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import com.example.musicplayerapp.connection.MessengerConnection.Companion.MESSENGER_CONNECTION_INIT_CODE

class HandlerReplyMessage : Handler(Looper.getMainLooper()) {
    override fun handleMessage(msg: Message) {
        super.handleMessage(msg)

        when(msg.what) {
            MESSENGER_CONNECTION_INIT_CODE -> {
                // 재생 후 지속 시간이 전송 되면
                val bundle = msg.obj as Bundle
                bundle.getInt(HANDLER_REPLY_MESSAGE_DURATION_KEY)?.let {
                    when {
                        it > 0 -> {
                            Log.d(HANDLER_REPLY_MESSAGE_TAG, "duration : $it")
                        } else -> {
                            Log.d(HANDLER_REPLY_MESSAGE_TAG, "Not play")
                        }
                    }
                }
            }
        }
    }

    companion object {
        const val HANDLER_REPLY_MESSAGE_TAG = "HandlerReplyMessage"
        const val HANDLER_REPLY_MESSAGE_DURATION_KEY = "duration"
    }
}