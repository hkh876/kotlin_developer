package com.example.googlemapapp

import android.app.Application
import com.example.googlemapapp.data.AppContainer
import com.example.googlemapapp.data.DefaultAppContainer

class GoogleMapApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
    }
}