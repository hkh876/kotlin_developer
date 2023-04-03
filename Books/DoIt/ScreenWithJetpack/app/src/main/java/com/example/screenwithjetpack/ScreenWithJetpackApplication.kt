package com.example.screenwithjetpack

import android.app.Application
import com.example.screenwithjetpack.data.AppContainer
import com.example.screenwithjetpack.data.AppDataContainer

class ScreenWithJetpackApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}