package com.example.screenwithjetpackex

import android.app.Application
import com.example.screenwithjetpackex.data.AppContainer
import com.example.screenwithjetpackex.data.AppDataContainer

class ScreenWithJetpackExApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}