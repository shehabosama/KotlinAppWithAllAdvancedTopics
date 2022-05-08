package com.example.bokemonappwithkotlinandadvancedtech.di

import android.content.Context
import androidx.room.Room
import com.example.bokemonappwithkotlinandadvancedtech.db.BlogDao
import com.example.bokemonappwithkotlinandadvancedtech.db.BlogDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Singleton
    @Provides
    fun provedBlogDb(@ApplicationContext context: Context):BlogDatabase{
        return Room.databaseBuilder(context , BlogDatabase::class.java , BlogDatabase.DATABASE_NAME )
            .fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideBlogDAO(blogDatabase: BlogDatabase):BlogDao{
        return blogDatabase.blogDao()
    }
}