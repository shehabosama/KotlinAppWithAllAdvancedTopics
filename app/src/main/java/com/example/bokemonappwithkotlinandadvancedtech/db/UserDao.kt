package com.example.bokemonappwithkotlinandadvancedtech.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bokemonappwithkotlinandadvancedtech.model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userEntity:UserCashEntity):Long

    @Query("SELECT * FROM users")
    suspend fun getUsers():List<UserCashEntity>

}