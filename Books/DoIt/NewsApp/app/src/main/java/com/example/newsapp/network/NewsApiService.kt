package com.example.newsapp.network

import com.example.newsapp.model.PageListModel
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("/v2/everything")
    suspend fun getNews(
        @Query("q") q: String?,
        @Query("apiKey") apiKey: String?,
        @Query("page") page: Long,
        @Query("pageSize") pageSize: Int,
    ): PageListModel
}