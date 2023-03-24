package com.example.amphibiansapp.data

import com.example.amphibiansapp.network.AmphibiansApiService
import com.example.amphibiansapp.network.Amphibian

interface AmphibiansRepository {
    suspend fun getAmphibians(): List<Amphibian>
}

class NetworkAmphibiansRepository(
    private val amphibiansApiService: AmphibiansApiService
) : AmphibiansRepository {
    override suspend fun getAmphibians(): List<Amphibian> {
        return amphibiansApiService.getAmphibians()
    }
}