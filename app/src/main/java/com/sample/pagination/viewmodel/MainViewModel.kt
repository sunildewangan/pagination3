package com.sample.pagination.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sample.pagination.model.Movie
import com.sample.pagination.repository.MainRepository

class MainViewModel constructor(
    private val mainRepository: MainRepository
): ViewModel() {
    val errorMessage = MutableLiveData<String>()
    fun getMovieList(): LiveData<PagingData<Movie>>{
        return mainRepository.getAllMovies().cachedIn(viewModelScope)
    }
}