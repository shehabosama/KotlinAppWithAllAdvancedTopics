package com.example.bokemonappwithkotlinandadvancedtech.repository

import android.util.Log
import com.example.bokemonappwithkotlinandadvancedtech.db.BlogDao
import com.example.bokemonappwithkotlinandadvancedtech.db.CacheMapper
import com.example.bokemonappwithkotlinandadvancedtech.model.Blog
import com.example.bokemonappwithkotlinandadvancedtech.network.BlogRetrofitAPI
import com.example.bokemonappwithkotlinandadvancedtech.network.NetworkMapper
import com.example.bokemonappwithkotlinandadvancedtech.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BlogRepository
@Inject
constructor(
    private val retrofit: BlogRetrofitAPI ,
    private val blogDao: BlogDao,
    private val cacheMapper: CacheMapper ,
    private val networkMapper: NetworkMapper)
{
    suspend fun getBlogs(): Flow<DataState<List<Blog>>> = flow {
        emit(DataState.Loading)
        delay(1000)
        try{
            Log.e("Tracking", "under delay" )
            val networkBlogs = retrofit.getBlog()
            Log.e("Tracking", "under getBlog"+networkBlogs.toString() )
            val blogs = networkMapper.mapFromEntityEntityList(networkBlogs)
            Log.e("Tracking", "under mapFromEntity" )
            for(blog in blogs){
                blogDao.insertBlog(cacheMapper.mapToEntity(blog))
            }
            val cachedBlogs = blogDao.getBlog()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cachedBlogs)))
        }catch (e: Exception){
            Log.e("error", "getBlogs: ",e.fillInStackTrace() )
            emit(DataState.Error(e))
        }
    }
}