package com.example.myapplication.provider

import com.example.myapplication.model.NewsApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = "2ce47044cc0a48b7ac9c04b4cf226056"

interface NewsProvider {

    @GET("top-headlines?apiKey=$API_KEY")
    suspend fun topHeadLines(@Query("country")country:String):Response<NewsApiResponse>
}