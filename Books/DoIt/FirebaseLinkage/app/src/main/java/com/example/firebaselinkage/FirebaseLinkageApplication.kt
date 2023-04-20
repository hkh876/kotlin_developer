package com.example.firebaselinkage

import android.app.Application
import com.example.firebaselinkage.data.AppContainer
import com.example.firebaselinkage.data.DefaultAppContainer

class FirebaseLinkageApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
    }
}