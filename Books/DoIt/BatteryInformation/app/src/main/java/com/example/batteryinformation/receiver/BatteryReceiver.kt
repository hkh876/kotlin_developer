package com.example.batteryinformation.receiver

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.batteryinformation.R

class BatteryReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
           if (ContextCompat.checkSelfPermission(
                   context,
                   Manifest.permission.POST_NOTIFICATIONS
           ) == PackageManager.PERMISSION_GRANTED) {
               sendNotifications(context = context)
           }
        }
    }

    private fun sendNotifications(context: Context) {
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        var builder: NotificationCompat.Builder

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // over 26 version
            val channelId = BATTERY_RECEIVER_NOTIFY_CHANNEL_ID
            val channelName = BATTERY_RECEIVER_NOTIFY_CHANNEL_NAME
            val channel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                // 채널에 대한 다양한 정보 설정
                description = BATTERY_RECEIVER_NOTIFY_DESCRIPTION
                setShowBadge(true)

                val url = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                val audioAttributes = AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .build()

                setSound(url, audioAttributes)
                enableVibration(true)
            }

            // 채널을 Notification Manager에 등록
            manager.createNotificationChannel(channel)

            // 채널을 이용 하여 Builder 생성
            builder = NotificationCompat.Builder(context, channelId)
        } else {
            builder = NotificationCompat.Builder(context)
        }

        // 알림의 기본 정보
        builder.setSmallIcon(R.drawable.small)
            .setWhen(System.currentTimeMillis())
            .setContentTitle(BATTERY_RECEIVER_NOTIFY_CONTENT_TITLE)
            .setContentText(BATTERY_RECEIVER_NOTIFY_CONTENT_TEXT)
            .setLargeIcon(BitmapFactory.decodeResource(context.resources, R.drawable.big))

        manager.notify(BATTERY_RECEIVER_NOTIFY_ID, builder.build())
    }

    companion object {
        const val BATTERY_RECEIVER_NOTIFY_CHANNEL_ID = "Battery-channel-id-1"
        const val BATTERY_RECEIVER_NOTIFY_CHANNEL_NAME = "Battery-name-1"
        const val BATTERY_RECEIVER_NOTIFY_DESCRIPTION = "Battery receiver notify description"
        const val BATTERY_RECEIVER_NOTIFY_CONTENT_TITLE = "태평장사꾼"
        const val BATTERY_RECEIVER_NOTIFY_CONTENT_TEXT = "안녕하세요."
        const val BATTERY_RECEIVER_NOTIFY_ID = 11
    }
}