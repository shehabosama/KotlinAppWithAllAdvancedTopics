package com.example.bokemonappwithkotlinandadvancedtech.di

import com.example.bokemonappwithkotlinandadvancedtech.db.BlogDao
import com.example.bokemonappwithkotlinandadvancedtech.db.BlogCacheMapper
import com.example.bokemonappwithkotlinandadvancedtech.db.UserCacheMapper
import com.example.bokemonappwithkotlinandadvancedtech.db.UserDao
import com.example.bokemonappwithkotlinandadvancedtech.network.RetrofitAPI
import com.example.bokemonappwithkotlinandadvancedtech.network.BlogMapper
import com.example.bokemonappwithkotlinandadvancedtech.network.UserNetworkMapper
import com.example.bokemonappwithkotlinandadvancedtech.repository.BlogRepository
import com.example.bokemonappwithkotlinandadvancedtech.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideBlogRepository(blogDao: BlogDao,
                              retrofitAPI: RetrofitAPI,
                              blogCacheMapper: BlogCacheMapper,
                              blogMapper: BlogMapper ):BlogRepository {
        return BlogRepository(retrofitAPI ,blogDao , blogCacheMapper,blogMapper )
    }

    @Singleton
    @Provides
    fun provideUserRepository(userDao: UserDao,
                              retrofitAPI: RetrofitAPI,
                              userCacheMapper: UserCacheMapper,
                              userNetworkMapper: UserNetworkMapper ):UserRepository {
        return UserRepository(retrofitAPI ,userDao , userCacheMapper,userNetworkMapper )
    }
}