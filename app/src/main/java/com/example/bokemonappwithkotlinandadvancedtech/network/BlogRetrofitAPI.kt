package com.example.bokemonappwithkotlinandadvancedtech.network

import retrofit2.http.GET

interface BlogRetrofitAPI {
    @GET("blogs")
    suspend fun getBlog():List<BlogNetworkEntity>
}