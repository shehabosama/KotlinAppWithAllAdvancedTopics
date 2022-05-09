package com.example.bokemonappwithkotlinandadvancedtech.repository

import android.util.Log
import com.example.bokemonappwithkotlinandadvancedtech.db.BlogDao
import com.example.bokemonappwithkotlinandadvancedtech.db.BlogCacheMapper
import com.example.bokemonappwithkotlinandadvancedtech.model.Blog
import com.example.bokemonappwithkotlinandadvancedtech.network.RetrofitAPI
import com.example.bokemonappwithkotlinandadvancedtech.network.BlogMapper
import com.example.bokemonappwithkotlinandadvancedtech.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BlogRepository
@Inject
constructor(
    private val retrofit: RetrofitAPI,
    private val blogDao: BlogDao,
    private val blogCacheMapper: BlogCacheMapper,
    private val blogMapper: BlogMapper)
{
    suspend fun getBlogs(): Flow<DataState<List<Blog>>> = flow {
        emit(DataState.Loading)
        delay(1000)
        try{
            val networkBlogs = retrofit.getBlog()
            val blogs = blogMapper.mapFromEntityEntityList(networkBlogs)
            for(blog in blogs){
                blogDao.insertBlog(blogCacheMapper.mapToEntity(blog))
            }
            val cachedBlogs = blogDao.getBlog()
            emit(DataState.Success(blogCacheMapper.mapFromEntityList(cachedBlogs)))
        }catch (e: Exception){
            Log.e("error", "getBlogs: ",e.fillInStackTrace() )
            emit(DataState.Error(e))
        }
    }
}