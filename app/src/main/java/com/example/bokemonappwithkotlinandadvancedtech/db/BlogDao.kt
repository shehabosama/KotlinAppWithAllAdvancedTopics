package com.example.bokemonappwithkotlinandadvancedtech.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BlogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBlog(blogEntity:BlogCashEntity):Long

    @Query("SELECT * FROM blogs")
    suspend fun getBlog():List<BlogCashEntity>
}