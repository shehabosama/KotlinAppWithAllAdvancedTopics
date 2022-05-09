package com.example.bokemonappwithkotlinandadvancedtech.di

import com.example.bokemonappwithkotlinandadvancedtech.network.RetrofitAPI
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    /**
    WE COMMENTED IT BECAUSE WE ALREADY Inject IT THERE IN ITS CLASS
    @Singleton
    @Provides
    fun ProvideNetworkMapper():EntityMapper<BlogNetworkEntity , Blog>{
        return NetworkMapper()
    }
    */


    // Gson object responsible for parsing gson data to java objects
    @Singleton
    @Provides
    fun provideGsonBuilder():Gson{
        return GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
        // "excludeFieldsWithoutExposeAnnotation()" if there is properties with out @Expose Annotation will ignore it
    }

    @Singleton
    @Provides

    fun provideRetrofit(gson: Gson):Retrofit.Builder {
        return Retrofit.Builder().baseUrl("https://open-api.xyz/placeholder/").addConverterFactory(/*this for converting gson to java object*/GsonConverterFactory.create(gson))
    }
    @Singleton
    @Provides
    fun provideAPIService(retrofit: Retrofit.Builder):RetrofitAPI{
        return retrofit.build().create(RetrofitAPI::class.java)
    }


}