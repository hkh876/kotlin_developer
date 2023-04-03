package com.example.phonenumberkeypad

import android.app.Application
import com.example.phonenumberkeypad.data.AppContainer
import com.example.phonenumberkeypad.data.AppDataContainer

class PhoneNumberKeypadApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}