package com.example.notificationswithreply.receiver

import android.app.NotificationManager
import android.app.RemoteInput
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class ReplyReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        // 알림의 입력 글 획득
        val replyText = RemoteInput.getResultsFromIntent(intent)
            .getCharSequence(KEY_TEXT_REPLY)

        Log.d(REPLY_RECEIVER_TAG, "Reply text : $replyText")

        // 알림 취소
        val manager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.cancel(NOTIFICATION_ID)
    }

    companion object {
        const val REPLY_RECEIVER_TAG = "ReplyReceiver"
        const val KEY_TEXT_REPLY = "key_text_reply"
        const val NOTIFICATION_ID = 11
        const val REQUEST_CODE = 30
    }
}