package com.example.flightsearch.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FlightSearchItemDao {
    @Query("SELECT * from airport ORDER BY passengers DESC")
    fun getAllItems(): Flow<List<FlightSearchItem>>
}

@Dao
interface FlightFavoriteItemDao {
    @Query("SELECT * from favorite")
    fun getAllFavorites(): Flow<List<FlightFavoriteItem>>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavorite(favoriteItem: FlightFavoriteItem)
    @Query("DELETE from favorite WHERE departure_code = :departCode AND destination_code = :destinationCode")
    suspend fun deleteFavoriteByCode(departCode: String, destinationCode: String)
}