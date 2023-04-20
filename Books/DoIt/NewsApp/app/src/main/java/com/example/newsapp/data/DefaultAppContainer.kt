package com.example.newsapp.data

import android.content.Context
import com.example.newsapp.network.NewsApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

class DefaultAppContainer(private val context: Context) : AppContainer {
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(
            Json{ ignoreUnknownKeys = true }.asConverterFactory("application/json".toMediaType())
        )
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService: NewsApiService by lazy {
        retrofit.create(NewsApiService::class.java)
    }

    companion object {
        const val BASE_URL = "https://newsapi.org"
        const val QUERY = "travel"
        const val API_KEY = "079dac74a5f94ebdb990ecf61c8854b7"
        const val DEFAULT_PAGE = 1L
        const val DEFAULT_PAGE_SIZE = 10
    }

    override val newsRepository: NewsRepository by lazy {
        NetworkNewsRepository(retrofitService)
    }
}