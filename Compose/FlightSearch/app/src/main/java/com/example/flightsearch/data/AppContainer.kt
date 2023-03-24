package com.example.flightsearch.data

import android.content.Context

interface AppContainer {
    val itemsRepository: ItemsRepository
    val favoriteRepository: FavoritesRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val itemsRepository: ItemsRepository by lazy {
        OfflineItemsRepository(FlightSearchDatabase.getDatabase(context).FlightSearchItemDao())
    }

    override val favoriteRepository: FavoritesRepository by lazy {
        OfflineFavoritesRepository(FlightSearchDatabase.getDatabase(context).FlightFavoriteItemDao())
    }
}