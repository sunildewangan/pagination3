package com.sample.pagination.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.sample.pagination.api.RetrofitService
import com.sample.pagination.model.Movie
import com.sample.pagination.source.MoviePagingSource

class MainRepository constructor(
    private val retrofitService: RetrofitService
) {

    fun getAllMovies():LiveData<PagingData<Movie>>{
        return Pager(
            config = PagingConfig(
                pageSize = 5,
                enablePlaceholders = false,
                initialLoadSize = 2
            ),
            pagingSourceFactory = {
                MoviePagingSource(retrofitService)
            },
            initialKey = 1,
        ).liveData
    }
}