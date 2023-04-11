package com.example.musicplayerservice

import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.*
import android.util.Log

class MusicPlayerMessengerService : Service() {
    lateinit var player: MediaPlayer
    lateinit var messenger: Messenger
    lateinit var replyMessenger: Messenger

    override fun onCreate() {
        super.onCreate()
        player = MediaPlayer()
    }

    inner class IncomingHandler(
        context: Context,
        private val applicationContext: Context = context.applicationContext
    ) : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                MESSENGER_SERVICE_INIT_CODE -> {
                    // 서비스에 연결되자마자 전달되는 메시지
                    replyMessenger = msg.replyTo

                    if (!player.isPlaying) {
                        player = MediaPlayer.create(this@MusicPlayerMessengerService, R.raw.music)

                        try {
                            // 지속 시간 전송
                            val replyMsg = Message()
                            replyMsg.what = MESSENGER_SERVICE_INIT_CODE

                            val replyBundle = Bundle()
                            replyBundle.putInt(MESSENGER_SERVICE_DURATION_KEY, player.duration)

                            replyMsg.obj = replyBundle
                            replyMessenger.send(replyMsg)

                            // 음악 재생
                            player.start()
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
                MESSENGER_SERVICE_STOP_CODE -> {
                    // 멈춤 메시지
                    if (player.isPlaying) {
                        player.stop()
                    }
                }
                else -> {
                    super.handleMessage(msg)
                }
            }
        }
    }

    override fun onBind(intent: Intent): IBinder {
        messenger = Messenger(IncomingHandler(this))
        return messenger.binder
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }

    companion object {
        const val MESSENGER_SERVICE_DURATION_KEY = "duration"
        const val MESSENGER_SERVICE_INIT_CODE = 10
        const val MESSENGER_SERVICE_STOP_CODE = 20
    }
}