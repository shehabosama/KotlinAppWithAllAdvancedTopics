package com.example.bokemonappwithkotlinandadvancedtech.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserCashEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id:Int,
    @ColumnInfo(name = "username")
    val username:String,
    @ColumnInfo(name = "email")
    val email:String,
    @ColumnInfo(name = "password")
    val password:String,
    @ColumnInfo(name = "location")
    val location:String,
    @ColumnInfo(name = "is_admin")
    val is_admin:Int,
    @ColumnInfo(name = "is_bloked")
    val is_bloked:Int,
    @ColumnInfo(name = "user_token")
    val user_token:String,

)
