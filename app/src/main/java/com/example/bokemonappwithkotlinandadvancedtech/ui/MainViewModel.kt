package com.example.bokemonappwithkotlinandadvancedtech.ui

import androidx.lifecycle.*
import com.example.bokemonappwithkotlinandadvancedtech.model.Blog
import com.example.bokemonappwithkotlinandadvancedtech.model.User
import com.example.bokemonappwithkotlinandadvancedtech.repository.BlogRepository
import com.example.bokemonappwithkotlinandadvancedtech.repository.UserRepository
import com.example.bokemonappwithkotlinandadvancedtech.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
@ExperimentalCoroutinesApi
@HiltViewModel
class MainViewModel @Inject constructor(private val blogRepository: BlogRepository , private val userRepository: UserRepository) :ViewModel(){

    private val _dataBlogState:MutableLiveData<DataState<List<Blog>>> = MutableLiveData()
    val dataBlogState:LiveData<DataState<List<Blog>>>
    get() = _dataBlogState


    private val _dataUserState:MutableLiveData<DataState<List<User>>> = MutableLiveData()
    val dataUserState:LiveData<DataState<List<User>>>
        get() = _dataUserState

    fun setStateEvent(mainStateEvent: MainStateEvent){
        viewModelScope.launch {
            when(mainStateEvent){
                is MainStateEvent.GetBlogEvent-> {
                    blogRepository.getBlogs().onEach { dataState ->
                        _dataBlogState.value = dataState
                    }.launchIn(viewModelScope)
                }
                is MainStateEvent.GetUsersEvent->{
                    userRepository.getUsers().onEach { dataState ->
                        _dataUserState.value = dataState
                    }.launchIn(viewModelScope)
                }

                is MainStateEvent.None->{
                    //who cares
                }

            }
        }
    }

}
sealed class MainStateEvent{
    object GetBlogEvent:MainStateEvent() // state to get data from data source
    object GetUsersEvent:MainStateEvent()
    object None:MainStateEvent() // optional if you want do anything else
}