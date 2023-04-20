package com.example.firebaselinkage.firebase

import android.content.Context
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class FirebaseApplication(private val context: Context) {
    var auth: FirebaseAuth

    init {
        FirebaseApp.initializeApp(context)
        auth = Firebase.auth
    }
}