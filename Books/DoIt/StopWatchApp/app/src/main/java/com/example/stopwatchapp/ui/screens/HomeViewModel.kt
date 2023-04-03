package com.example.stopwatchapp.ui.screens

import android.os.CountDownTimer
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.stopwatchapp.data.msToTimeFormat

class HomeViewModel : ViewModel() {
    var homeUiState by mutableStateOf(HomeUiState())

    private var countDownTimer: CountDownTimer? = null

    fun startTimer() {
        val maxTime = MAX_TIME - homeUiState.pauseTime
        countDownTimer = object : CountDownTimer(maxTime, TIMER_INTERVAL) {
            override fun onTick(millisUntilFinished: Long) {
                val currentMs = maxTime - millisUntilFinished + homeUiState.pauseTime
                val toTime = msToTimeFormat(currentMs)
                homeUiState = homeUiState.copy(
                    currentTime = currentMs,
                    timeValue = toTime,
                    startIsEnable = false,
                    stopIsEnabled = true
                )
            }

            override fun onFinish() {
                Log.i(HOME_VIEW_MODEL_TAG, "Timer is done!")
                resetTimer()
            }
        }.start()
    }

    fun stopTimer() {
        countDownTimer?.cancel()
        homeUiState = homeUiState.copy(
            pauseTime = homeUiState.currentTime,
            startIsEnable = true,
            stopIsEnabled = false
        )
    }

    fun resetTimer() {
        countDownTimer?.cancel()
        homeUiState = HomeUiState()
    }

    companion object {
        const val HOME_VIEW_MODEL_TAG = "HomeViewModel"
        const val MAX_TIME = 60 * 60 * 1000L // 1 hours
        const val TIMER_INTERVAL = 1000L // 1 seconds
    }
}