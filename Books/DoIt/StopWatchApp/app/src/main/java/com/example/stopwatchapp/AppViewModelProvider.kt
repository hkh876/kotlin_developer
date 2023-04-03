package com.example.stopwatchapp

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.stopwatchapp.ui.screens.HomeViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel()
        }
    }
}

fun CreationExtras.stopWatchApplication(): StopWatchApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as StopWatchApplication)