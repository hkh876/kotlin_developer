package com.example.notificationswithreply

import android.app.Application
import com.example.notificationswithreply.data.AppContainer
import com.example.notificationswithreply.data.AppDataContainer

class NotificationsWithReplyApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}