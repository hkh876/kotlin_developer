package com.example.dessertclicker

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.model.Dessert
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DessertViewModel: ViewModel() {
    // UI State
    private val _uiState = MutableStateFlow(DessertUiState())
    val uiState: StateFlow<DessertUiState> = _uiState.asStateFlow()

    fun updateDessertState(desserts: List<Dessert>) {
        val increasedSold = uiState.value.dessertsSold.inc()
        val dessert = determineDessertToShow(
            desserts,
            increasedSold
        )

        _uiState.update {
            currentState -> currentState.copy(
                revenue = currentState.revenue + uiState.value.currentDessertPrice,
                dessertsSold = increasedSold,
                currentDessertPrice = dessert.price,
                currentDessertImageId = dessert.imageId
            )
        }
    }
}