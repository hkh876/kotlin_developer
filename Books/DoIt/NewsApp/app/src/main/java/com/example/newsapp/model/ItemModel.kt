package com.example.newsapp.model

import kotlinx.serialization.Serializable

@Serializable
data class ItemModel(
    val id: Long = 0,
    val author: String = "",
    val title: String = "",
    val description: String = "",
    val urlToImage: String = "",
    val publishedAt: String = ""
)
