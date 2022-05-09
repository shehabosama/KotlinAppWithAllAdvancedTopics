package com.example.bokemonappwithkotlinandadvancedtech.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.bokemonappwithkotlinandadvancedtech.R
import com.example.bokemonappwithkotlinandadvancedtech.model.Blog
import com.example.bokemonappwithkotlinandadvancedtech.model.User
import com.example.bokemonappwithkotlinandadvancedtech.util.DataState
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private val viewModel:MainViewModel by viewModels()
    private lateinit var text:TextView
    private lateinit var progressBar:ProgressBar
    lateinit var sb:StringBuilder
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        text = findViewById(R.id.text)
        progressBar = findViewById(R.id.progressBar)
         sb = StringBuilder()
        subscribeObserver()
        viewModel.setStateEvent(MainStateEvent.GetBlogEvent)

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed(Runnable {
            //Do something after 100ms
            viewModel.setStateEvent(MainStateEvent.GetUsersEvent)
        }, 5000)


    }
    private fun subscribeObserver(){
        viewModel.dataBlogState.observe( this, Observer { dataState->
            when(dataState){
                is DataState.Success<List<Blog>> ->{
                    //do some funciton
                    displayProgressBar(false)
                    appendBlogTitle(dataState.data)
                }
                is DataState.Error ->{
                    // handel error
                    displayProgressBar(false)
                    displayError(dataState.exception.message )
                }
                is DataState.Loading->{
                    //make progress
                    displayProgressBar(true)
                }
            }
        })
        viewModel.dataUserState.observe(this , Observer { dataState->
            when(dataState){
                is DataState.Success<List<User>> ->{
                    displayProgressBar(false)
                    appendUsername(dataState.data)
                }
                is DataState.Error ->{
                    // handel error
                    displayProgressBar(false)
                    displayError(dataState.exception.message )
                }
                is DataState.Loading->{
                    //make progress
                    displayProgressBar(true)
                }
            }
        })
    }
    private fun displayError(message:String?){
        if(message !=null){
            text.text = message
        }else{
            text.text = "Unknown error"
        }
    }
    private fun displayProgressBar(isDisplayed:Boolean){
        progressBar.visibility = if(isDisplayed) View.VISIBLE else View.GONE
    }
    private fun appendBlogTitle(blogs:List<Blog>){

        for(blog in blogs){
            sb.append(blog.title+"\n")
        }
        text.text = sb.toString()
    }
    private fun appendUsername(users:List<User>){

        for(user in users){
            sb.append(user.username+"\n")
        }
        text.text = sb.toString()
    }
}