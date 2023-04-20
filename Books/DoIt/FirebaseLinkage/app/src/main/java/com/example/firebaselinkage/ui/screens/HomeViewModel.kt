package com.example.firebaselinkage.ui.screens

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.firebaselinkage.R
import com.example.firebaselinkage.firebase.FirebaseApplication

class HomeViewModel(private val firebaseApplication : FirebaseApplication) : ViewModel() {
    var homeUiState by mutableStateOf(HomeUiState())
        private set

    fun updateUiState(newHomeUiState: HomeUiState) {
        homeUiState = newHomeUiState
    }

    fun login(context: Context) {
        if (homeUiState.email.isNotEmpty() && homeUiState.password.isNotEmpty()) {
            firebaseApplication.auth.signInWithEmailAndPassword(
                homeUiState.email,
                homeUiState.password
            ).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(HOME_VIEW_MODEL_TAG, "Login success")
                    homeUiState = homeUiState.copy(successLogin = true)
                } else {
                    Toast.makeText(
                        context,
                        context.getString(R.string.login_failed),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    fun clear() {
        homeUiState = HomeUiState()
    }

    companion object {
        const val HOME_VIEW_MODEL_TAG = "HomeViewModel"
    }
}