package com.example.musicplayerapp

import android.content.Context
import android.content.Intent
import android.os.Message
import android.os.Messenger
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.musicplayerapp.connection.AIDLConnection
import com.example.musicplayerapp.connection.MessengerConnection
import com.example.musicplayerapp.connection.MessengerConnection.Companion.MESSENGER_CONNECTION_STOP_CODE
import com.example.musicplayerapp.data.CommonValue.ACTION_SERVICE_AIDL
import com.example.musicplayerapp.data.CommonValue.ACTION_SERVICE_MESSENGER
import com.example.musicplayerapp.data.CommonValue.ACTION_SERVICE_PACKAGE_NAME
import com.example.musicplayerapp.handler.HandlerReplyMessage
import com.example.musicplayerapp.ui.screens.HomeScreen

@Composable
fun MusicPlayerApp() {
    val context = LocalContext.current
    val messengerIntent = Intent(ACTION_SERVICE_MESSENGER)
    messengerIntent.setPackage(ACTION_SERVICE_PACKAGE_NAME)

    val aidlIntent = Intent(ACTION_SERVICE_AIDL)
    aidlIntent.setPackage(ACTION_SERVICE_PACKAGE_NAME)

    val messengerConnection = MessengerConnection()
    messengerConnection.replyMessenger = Messenger(HandlerReplyMessage())

    val aidlConnection = AIDLConnection()

    HomeScreen(
        onMessengerPlayClick = {
            context.bindService(
                messengerIntent,
                messengerConnection,
                Context.BIND_AUTO_CREATE
            )
        },
        onMessengerPlayStopClick = {
            val msg = Message()
            msg.what = MESSENGER_CONNECTION_STOP_CODE

            messengerConnection.messenger.send(msg)
            context.unbindService(messengerConnection)
        },
        onAIDLPlayClick = {
            context.bindService(
                aidlIntent,
                aidlConnection,
                Context.BIND_AUTO_CREATE
            )
        },
        onAIDLPlayStopClick = {
            aidlConnection.aidlService.stop()
            context.unbindService(aidlConnection)
        }
    )
}