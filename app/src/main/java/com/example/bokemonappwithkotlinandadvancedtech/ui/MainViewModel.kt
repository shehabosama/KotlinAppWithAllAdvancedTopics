package com.example.bokemonappwithkotlinandadvancedtech.ui

import androidx.lifecycle.*
import com.example.bokemonappwithkotlinandadvancedtech.model.Blog
import com.example.bokemonappwithkotlinandadvancedtech.repository.BlogRepository
import com.example.bokemonappwithkotlinandadvancedtech.util.DataState
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
@ExperimentalCoroutinesApi
@HiltViewModel
class MainViewModel @Inject constructor(private val blogRepository: BlogRepository) :ViewModel(){

    private val _dataState:MutableLiveData<DataState<List<Blog>>> = MutableLiveData()
    val dataState:LiveData<DataState<List<Blog>>>
    get() = _dataState

    fun setStateEvent(mainStateEvent: MainStateEvent){
        viewModelScope.launch {
            when(mainStateEvent){
                is MainStateEvent.GetBlogEvent-> {
                    blogRepository.getBlogs().onEach { dataState ->
                        _dataState.value = dataState
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
    object None:MainStateEvent() // optional if you want do anything else
}