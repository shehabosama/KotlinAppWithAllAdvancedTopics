package com.example.bokemonappwithkotlinandadvancedtech.repository

import android.util.Log
import com.example.bokemonappwithkotlinandadvancedtech.db.*
import com.example.bokemonappwithkotlinandadvancedtech.model.User
import com.example.bokemonappwithkotlinandadvancedtech.network.RetrofitAPI
import com.example.bokemonappwithkotlinandadvancedtech.network.UserNetworkMapper
import com.example.bokemonappwithkotlinandadvancedtech.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepository @Inject constructor(private val retrofit: RetrofitAPI,
                                             private val userDao: UserDao,
                                             private val userCacheMapper: UserCacheMapper,
                                             private val userNetworkMapper: UserNetworkMapper
) {
    suspend fun getUsers():Flow<DataState<List<User>>> = flow {
        emit(DataState.Loading)
        delay(1000)
        try {
            var getNetworkUsers = retrofit.getAllUsers()
            var users = userNetworkMapper.mapFromUserNetworkEntityList(getNetworkUsers)
            for(user in users){
                userDao.insertUser(userCacheMapper.mapToEntity(user))
            }
            val cacheUser = userDao.getUsers()
             emit(DataState.Success(userCacheMapper.mapFromEntityList(cacheUser)))
        }catch (ex:Exception){
            Log.d("getUsers", "getUsers: "+ex.localizedMessage)
            emit(DataState.Error(ex))
        }

    }
}