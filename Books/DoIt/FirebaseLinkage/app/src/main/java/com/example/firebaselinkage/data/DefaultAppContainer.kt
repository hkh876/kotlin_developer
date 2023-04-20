package com.example.firebaselinkage.data

import android.content.Context
import com.example.firebaselinkage.firebase.FirebaseApplication

class DefaultAppContainer(private val context: Context) : AppContainer {
    override val firebaseApplication: FirebaseApplication by lazy {
        FirebaseApplication(context)
    }
}