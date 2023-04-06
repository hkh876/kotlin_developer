package com.example.batteryinformation.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    var homeUiState by mutableStateOf(HomeUiState())
        private set

    fun updateUiState(newUiState: HomeUiState) {
        homeUiState = newUiState
    }
}