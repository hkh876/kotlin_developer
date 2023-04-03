package com.example.kakaotalkpasswordconfirmation

import android.app.Application
import com.example.kakaotalkpasswordconfirmation.data.AppContainer
import com.example.kakaotalkpasswordconfirmation.data.AppDataContainer

class KaKaoTalkPasswordConfirmApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}