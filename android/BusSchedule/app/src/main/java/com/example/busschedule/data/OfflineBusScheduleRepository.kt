package com.example.busschedule.data

import kotlinx.coroutines.flow.Flow

class OfflineBusScheduleRepository(private val busScheduleDao: BusScheduleDao) : BusScheduleRepository {
    override fun getAllBusSchedules(): Flow<List<BusSchedule>> {
        return busScheduleDao.getAllBusSchedules()
    }

    override fun getBusScheduleByStopName(stopName: String): Flow<List<BusSchedule>> {
        return busScheduleDao.getBusScheduleByStopName(stopName)
    }
}