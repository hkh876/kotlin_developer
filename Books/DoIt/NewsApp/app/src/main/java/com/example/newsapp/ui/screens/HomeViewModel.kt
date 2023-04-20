package com.example.newsapp.ui.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.NewsRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val newsRepository: NewsRepository) : ViewModel() {
    var homeUiState: HomeUiState by mutableStateOf(HomeUiState())
        private set

    init {
        getNews()
    }

    private fun getNews() {
        viewModelScope.launch {
            homeUiState = try {
                HomeUiState(
                    newsRepository.getNews()
                )
            } catch (e: Exception) {
                Log.d("KH", "exception : ${ e.printStackTrace() }")
                HomeUiState()
            }
        }
    }
}