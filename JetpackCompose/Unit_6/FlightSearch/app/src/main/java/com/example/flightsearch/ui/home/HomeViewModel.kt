package com.example.flightsearch.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.example.flightsearch.data.FavoritesRepository
import com.example.flightsearch.data.FlightFavoriteItem
import com.example.flightsearch.data.FlightSearchItem
import com.example.flightsearch.data.ItemsRepository
import kotlinx.coroutines.flow.*

class HomeViewModel(
    private val itemsRepository: ItemsRepository,
    private val favoritesRepository: FavoritesRepository,
) : ViewModel() {
    fun getAllItems(): Flow<List<FlightSearchItem>> = itemsRepository.getAllItems()
    fun getFavorites(): Flow<List<FlightFavoriteItem>> = favoritesRepository.getAllFavorites()

    var homeUiState by mutableStateOf(HomeUiState())
    var homeFavoriteUiState by mutableStateOf(HomeFavoriteUiState())

    fun updateHomeUiState(newHomeUiState: HomeUiState) {
        homeUiState = newHomeUiState
    }

    fun updateHomeFavoriteUiState(newHomeFavoriteUiState: HomeFavoriteUiState) {
        homeFavoriteUiState = newHomeFavoriteUiState
    }

    suspend fun insertFavoriteItem(favoriteItem: FlightFavoriteItem) {
        favoritesRepository.insertFavorite(favoriteItem)
    }

    suspend fun deleteFavoriteItemByCode(departCode: String, destinationCode: String) {
        favoritesRepository.deleteFavoriteByCode(departCode, destinationCode)
    }
}
