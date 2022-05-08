package com.example.bokemonappwithkotlinandadvancedtech.di

import com.example.bokemonappwithkotlinandadvancedtech.db.BlogDao
import com.example.bokemonappwithkotlinandadvancedtech.db.CacheMapper
import com.example.bokemonappwithkotlinandadvancedtech.network.BlogRetrofitAPI
import com.example.bokemonappwithkotlinandadvancedtech.network.NetworkMapper
import com.example.bokemonappwithkotlinandadvancedtech.repository.BlogRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideBlogRepository(blogDao: BlogDao ,
                              retrofitAPI: BlogRetrofitAPI ,
                              cacheMapper: CacheMapper ,
                              networkMapper: NetworkMapper ):BlogRepository {
        return BlogRepository(retrofitAPI ,blogDao , cacheMapper,networkMapper )
    }
}