package com.example.firebaselinkage.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.firebaselinkage.FirebaseLinkageApplication
import com.example.firebaselinkage.ui.screens.HomeViewModel
import com.example.firebaselinkage.ui.screens.LogOutViewModel
import com.example.firebaselinkage.ui.screens.SignUpViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(firebaseLinkageApplication().container.firebaseApplication)
        }

        initializer {
            SignUpViewModel(firebaseLinkageApplication().container.firebaseApplication)
        }

        initializer {
            LogOutViewModel(firebaseLinkageApplication().container.firebaseApplication)
        }
    }
}

fun CreationExtras.firebaseLinkageApplication(): FirebaseLinkageApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as FirebaseLinkageApplication)