package com.example.newsapp.data

import com.example.newsapp.data.DefaultAppContainer.Companion.API_KEY
import com.example.newsapp.data.DefaultAppContainer.Companion.DEFAULT_PAGE
import com.example.newsapp.data.DefaultAppContainer.Companion.DEFAULT_PAGE_SIZE
import com.example.newsapp.data.DefaultAppContainer.Companion.QUERY
import com.example.newsapp.model.PageListModel
import com.example.newsapp.network.NewsApiService

interface NewsRepository {
    suspend fun getNews(
        query: String = QUERY,
        apiKey: String = API_KEY,
        page: Long = DEFAULT_PAGE,
        pageSize: Int = DEFAULT_PAGE_SIZE
    ): PageListModel
}

class NetworkNewsRepository(
    private val newsApiService: NewsApiService
) : NewsRepository {
    override suspend fun getNews(
        query: String,
        apiKey: String,
        page: Long,
        pageSize: Int
    ): PageListModel {
        return newsApiService.getNews(
            query,
            apiKey,
            page,
            pageSize
        )
    }
}