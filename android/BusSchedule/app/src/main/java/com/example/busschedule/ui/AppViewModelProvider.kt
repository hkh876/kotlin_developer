package com.example.busschedule.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.busschedule.BusScheduleApplication

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            BusScheduleViewModel(busScheduleApplication().container.busSchedulesRepository)
        }
    }
}

fun CreationExtras.busScheduleApplication(): BusScheduleApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as BusScheduleApplication)