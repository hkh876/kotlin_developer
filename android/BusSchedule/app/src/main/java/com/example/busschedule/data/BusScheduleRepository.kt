package com.example.busschedule.data

import kotlinx.coroutines.flow.Flow

interface BusScheduleRepository {
    fun getAllBusSchedules(): Flow<List<BusSchedule>>
    fun getBusScheduleByStopName(stopName: String): Flow<List<BusSchedule>>
}