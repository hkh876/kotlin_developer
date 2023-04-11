package com.example.musicplayerservice

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder

class MusicPlayerAIDLService : Service() {
    lateinit var player: MediaPlayer

    override fun onCreate() {
        super.onCreate()
        player = MediaPlayer()
    }

    override fun onBind(intent: Intent): IBinder? {
       return object : MusicPlayerAidlInterface.Stub() {
            override fun basicTypes(
                anInt: Int,
                aLong: Long,
                aBoolean: Boolean,
                aFloat: Float,
                aDouble: Double,
                aString: String?
            ) {
                TODO("Not yet implemented")
            }

            override fun getMaxDuration(): Int {
                return if (player.isPlaying) {
                    player.duration
                } else {
                    0
                }
            }

            override fun start() {
                if (!player.isPlaying) {
                    player = MediaPlayer.create(this@MusicPlayerAIDLService, R.raw.music)
                }

                try {
                    player.start()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            override fun stop() {
                if (player.isPlaying) {
                    player.stop()
                }
            }
        }
    }

    override fun onDestroy() {
        player.release()
        super.onDestroy()
    }
}