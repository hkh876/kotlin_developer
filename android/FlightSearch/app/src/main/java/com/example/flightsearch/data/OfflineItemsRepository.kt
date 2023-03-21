package com.example.flightsearch.data

import kotlinx.coroutines.flow.Flow

class OfflineItemsRepository(private val itemDao: FlightSearchItemDao) : ItemsRepository {
    override fun getAllItems(): Flow<List<FlightSearchItem>> {
        return itemDao.getAllItems()
    }
}

class OfflineFavoritesRepository(private val itemDao: FlightFavoriteItemDao) : FavoritesRepository {
    override fun getAllFavorites(): Flow<List<FlightFavoriteItem>> {
        return itemDao.getAllFavorites()
    }

    override suspend fun insertFavorite(favoriteItem: FlightFavoriteItem) {
        return itemDao.insertFavorite(favoriteItem)
    }

    override suspend fun deleteFavoriteByCode(departCode: String, destinationCode: String) {
        return itemDao.deleteFavoriteByCode(departCode, destinationCode)
    }
}