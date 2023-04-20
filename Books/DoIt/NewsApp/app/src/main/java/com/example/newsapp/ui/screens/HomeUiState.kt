package com.example.newsapp.ui.screens

import com.example.newsapp.model.PageListModel

data class HomeUiState(
    val news: PageListModel = PageListModel()
)