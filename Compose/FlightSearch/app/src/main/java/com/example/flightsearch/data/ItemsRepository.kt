package com.example.flightsearch.data

import kotlinx.coroutines.flow.Flow

interface ItemsRepository {
    fun getAllItems(): Flow<List<FlightSearchItem>>
}

interface FavoritesRepository {
    fun getAllFavorites(): Flow<List<FlightFavoriteItem>>
    suspend fun insertFavorite(favoriteItem: FlightFavoriteItem)
    suspend fun deleteFavoriteByCode(departCode: String, destinationCode: String)
}