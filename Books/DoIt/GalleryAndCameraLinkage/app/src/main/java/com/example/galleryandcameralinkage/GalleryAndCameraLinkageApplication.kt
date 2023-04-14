package com.example.galleryandcameralinkage

import android.app.Application
import com.example.galleryandcameralinkage.data.AppContainer
import com.example.galleryandcameralinkage.data.AppDataContainer

class GalleryAndCameraLinkageApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}