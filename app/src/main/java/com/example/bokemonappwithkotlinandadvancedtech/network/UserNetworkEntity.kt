package com.example.bokemonappwithkotlinandadvancedtech.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserNetworkEntity(
    @SerializedName("id")
    @Expose
    var id:Int,
    @SerializedName("username")
    @Expose
    var username:String,
    @SerializedName("email")
    @Expose
    var email:String ,
    @SerializedName("password")
    @Expose
    var password:String,
    @SerializedName("location")
    @Expose
    var location:String,
    @SerializedName("is_admin")
    @Expose
    var is_admin:Int,
    @SerializedName("is_bloked")
    @Expose
    var is_bloked:Int,
    @SerializedName("user_token")
    @Expose
    var user_token:String,
    )  {


}