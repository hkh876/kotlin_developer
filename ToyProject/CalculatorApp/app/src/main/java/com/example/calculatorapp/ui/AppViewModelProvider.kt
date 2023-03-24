package com.example.calculatorapp.ui

import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.calculatorapp.ui.screens.HomeViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer { HomeViewModel() }
    }
}