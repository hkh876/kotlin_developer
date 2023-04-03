package com.example.stopwatchapp.ui.screens

data class HomeUiState(
    val currentTime: Long = 0L,
    val pauseTime: Long = 0L,
    val timeValue: String = "00:00",
    val startIsEnable: Boolean = true,
    val stopIsEnabled: Boolean = false,
    val resetIsEnabled: Boolean = true,
)
