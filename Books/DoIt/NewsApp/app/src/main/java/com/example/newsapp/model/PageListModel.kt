package com.example.newsapp.model

import kotlinx.serialization.Serializable

@Serializable
data class PageListModel(
    val articles: List<ItemModel> = listOf()
)
