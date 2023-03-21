package com.example.flightsearch.ui.home

import com.example.flightsearch.data.FlightFavoriteItem
import com.example.flightsearch.data.FlightSearchItem

data class HomeUiState(
    val query: String = "",
    val selectedItem: FlightSearchItem ?= null,
    val searchItemList: List<FlightSearchItem> = listOf()
)

data class HomeFavoriteUiState(
    val id: Int = 0,
    val departCode: String = "",
    val departName: String = "",
    val destinationCode: String = "",
    val destinationName: String = "",
    var isFavorite: Boolean = false,
)

fun HomeFavoriteUiState.toItem(): FlightFavoriteItem = FlightFavoriteItem(
    departureCode = departCode,
    destinationCode = destinationCode
)
