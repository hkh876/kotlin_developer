package com.example.notificationswithreply.ui.screens

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput
import androidx.core.content.ContextCompat
import com.example.notificationswithreply.R
import com.example.notificationswithreply.receiver.ReplyReceiver
import com.example.notificationswithreply.receiver.ReplyReceiver.Companion.KEY_TEXT_REPLY
import com.example.notificationswithreply.receiver.ReplyReceiver.Companion.NOTIFICATION_ID
import com.example.notificationswithreply.receiver.ReplyReceiver.Companion.REQUEST_CODE

@Composable
fun HomeScreen() {
    val context = LocalContext.current
    val permission = Manifest.permission.POST_NOTIFICATIONS
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        isGranted -> if (isGranted) {
            SendNotifications(context)
        } else {
            Toast.makeText(
                context,
                "permission denied...",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    if (ContextCompat.checkSelfPermission(
                            context,
                            permission
                        ) == PackageManager.PERMISSION_GRANTED) {
                        SendNotifications(context)
                    } else {
                        permissionLauncher.launch(permission)
                    }
                } else {
                    SendNotifications(context)
                }
            }
        ) {
            Text(text = stringResource(id = R.string.notify_button_text))
        }
    }
}

fun SendNotifications(context: Context) {
    val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    var builder: NotificationCompat.Builder

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        // 26 버전 이상
        val channelId = context.getString(R.string.channel_id)
        val channelName = context.getString(R.string.channel_name)
        val channel = NotificationChannel(
            channelId,
            channelName,
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            // 채널에 다양한 정보 설정
            description = context.getString(R.string.channel_description)
            setShowBadge(true)

            val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val audioAttributes = AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_ALARM)
                .build()

            setSound(uri, audioAttributes)
            enableVibration(true)
        }

        // 채널을 NotificationManager에 등록
        manager.createNotificationChannel(channel)

        // 채널을 이용 하여 builder 생성
        builder = NotificationCompat.Builder(context, channelId)
    } else {
        // 26 버전 이하
        builder = NotificationCompat.Builder(context)
    }

    // 알림의 기본 정보
    builder
        .setSmallIcon(R.drawable.small)
        .setWhen(System.currentTimeMillis())
        .setContentTitle(context.getString(R.string.notify_sender))
        .setContentText(context.getString(R.string.notify_contents))
        .setLargeIcon(BitmapFactory.decodeResource(context.resources, R.drawable.big))

    val replyLabel = context.getString(R.string.reply_label)
    val remoteInput = RemoteInput.Builder(KEY_TEXT_REPLY)
        .setLabel(replyLabel)
        .build()
    val replyIntent = Intent(
        context,
        ReplyReceiver::class.java
    )
    val replyPendingIntent = PendingIntent.getBroadcast(
        context,
        REQUEST_CODE,
        replyIntent,
        PendingIntent.FLAG_MUTABLE
    )

    builder.addAction(
        NotificationCompat.Action.Builder(
            R.drawable.send,
            replyLabel,
            replyPendingIntent
        ).addRemoteInput(remoteInput).build()
    )

    manager.notify(NOTIFICATION_ID, builder.build())
}