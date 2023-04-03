package com.example.stopwatchapp

import android.app.Application
import com.example.stopwatchapp.data.AppContainer
import com.example.stopwatchapp.data.AppDataContainer

class StopWatchApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}