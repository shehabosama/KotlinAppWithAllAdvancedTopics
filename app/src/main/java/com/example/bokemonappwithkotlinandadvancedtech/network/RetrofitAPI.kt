package com.example.bokemonappwithkotlinandadvancedtech.network

import retrofit2.http.GET
import retrofit2.http.Url

interface RetrofitAPI {
    @GET("blogs")
    suspend fun getBlog():List<BlogNetworkEntity>
    @GET()
    suspend fun getAllUsers(
        @Url baseUrl:String = "https://shehabosamafathi.000webhostapp.com/restaurant/get-all-user.php"):List<UserNetworkEntity>
}