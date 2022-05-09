package com.example.bokemonappwithkotlinandadvancedtech.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BlogCashEntity::class , UserCashEntity::class] , version = 2)
abstract class Database :RoomDatabase(){
    abstract fun blogDao():BlogDao
    abstract fun userDao():UserDao

    companion object{
        val DATABASE_NAME:String = "blog_db"
    }
}