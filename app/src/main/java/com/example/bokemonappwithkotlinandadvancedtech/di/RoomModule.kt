package com.example.bokemonappwithkotlinandadvancedtech.di

import android.content.Context
import androidx.room.Room
import com.example.bokemonappwithkotlinandadvancedtech.db.BlogDao
import com.example.bokemonappwithkotlinandadvancedtech.db.Database
import com.example.bokemonappwithkotlinandadvancedtech.db.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Singleton
    @Provides
    fun provedBlogDb(@ApplicationContext context: Context):Database{
        return Room.databaseBuilder(context , Database::class.java , Database.DATABASE_NAME )
            .fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideBlogDAO(database: Database):BlogDao{
        return database.blogDao()
    }
    @Singleton
    @Provides
    fun provideUserDAO(database: Database):UserDao{
        return database.userDao()
    }
}