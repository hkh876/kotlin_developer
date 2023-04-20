package com.example.firebaselinkage.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.firebaselinkage.firebase.FirebaseApplication

class LogOutViewModel(private val firebaseApplication: FirebaseApplication) : ViewModel() {
    var email by mutableStateOf("")

    init {
        getEmail()
    }

    private fun getEmail() {
        val user = firebaseApplication.auth.currentUser
        email = user?.email.toString()
    }

    fun logout() {
        firebaseApplication.auth.signOut()
    }
}