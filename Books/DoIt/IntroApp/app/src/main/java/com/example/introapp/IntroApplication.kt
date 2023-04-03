package com.example.introapp

import android.app.Application
import com.example.introapp.data.AppContainer
import com.example.introapp.data.AppDataContainer

class IntroApplication : Application() {
    lateinit var container : AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}