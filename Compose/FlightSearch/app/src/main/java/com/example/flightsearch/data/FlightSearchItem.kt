package com.example.flightsearch.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "airport")
data class FlightSearchItem(
    @PrimaryKey
    val id: Int = 0,
    val name: String,
    @NonNull
    @ColumnInfo(name = "iata_code")
    val iataCode: String,
    val passengers: Int
)

@Entity(tableName = "favorite")
data class FlightFavoriteItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @NonNull
    @ColumnInfo(name = "departure_code")
    val departureCode: String,
    @NonNull
    @ColumnInfo(name = "destination_code")
    val destinationCode: String
)
