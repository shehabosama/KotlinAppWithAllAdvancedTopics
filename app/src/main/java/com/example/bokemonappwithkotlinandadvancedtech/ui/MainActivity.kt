package com.example.bokemonappwithkotlinandadvancedtech.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.bokemonappwithkotlinandadvancedtech.R
import com.example.bokemonappwithkotlinandadvancedtech.model.Blog
import com.example.bokemonappwithkotlinandadvancedtech.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import java.lang.StringBuilder

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private val viewModel:MainViewModel by viewModels()
    private lateinit var text:TextView
    private lateinit var progressBar:ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        text = findViewById(R.id.text)
        progressBar = findViewById(R.id.progressBar)
        subscribeObserver()
        viewModel.setStateEvent(MainStateEvent.GetBlogEvent)
    }
    private fun subscribeObserver(){
        viewModel.dataState.observe( this, Observer { dataState->
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
        val sb = StringBuilder()
        for(blog in blogs){
            sb.append(blog.title+"\n")
        }
        text.text = sb.toString()
    }
}