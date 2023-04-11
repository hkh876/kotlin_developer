package com.example.musicplayerapp

import android.app.Application
import com.example.musicplayerapp.data.AppContainer
import com.example.musicplayerapp.data.AppDataContainer

class MusicPlayerApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}