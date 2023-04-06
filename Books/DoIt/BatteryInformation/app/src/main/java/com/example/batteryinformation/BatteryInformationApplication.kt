package com.example.batteryinformation

import android.app.Application
import com.example.batteryinformation.data.AppContainer
import com.example.batteryinformation.data.AppDataContainer

class BatteryInformationApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}