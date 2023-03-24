package com.example.calculatorapp.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    var homeUiState by mutableStateOf(HomeUiState())

    fun updateUiState(newHomeUiState: HomeUiState) {
        homeUiState = newHomeUiState
    }

    fun clearInputOutputText() {
        homeUiState = HomeUiState()
    }
}