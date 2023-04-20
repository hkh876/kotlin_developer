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

class SignUpViewModel(private val firebaseApplication: FirebaseApplication) : ViewModel() {
    var signUpUiState by mutableStateOf(SignUpUiState())
        private set

    fun signUp(
        email: String,
        password: String,
        context: Context
    ) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            firebaseApplication.auth
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(SIGN_UP_VIEW_MODEL_TAG, "Create user success")
                        signUpUiState = signUpUiState.copy(isSuccess = true)
                    } else {
                        Toast.makeText(
                            context,
                            context.getString(R.string.signup_failed),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }

    fun updateUiState(newSignUpUiState: SignUpUiState) {
        signUpUiState = newSignUpUiState
    }

    companion object {
        const val SIGN_UP_VIEW_MODEL_TAG = "SignUpViewModel"
    }
}