package com.example.busschedule.data

import android.content.Context

interface AppContainer {
    val busSchedulesRepository: BusScheduleRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val busSchedulesRepository: BusScheduleRepository by lazy {
        OfflineBusScheduleRepository(BusScheduleDatabase.getDatabase(context).busScheduleDao())
    }
}