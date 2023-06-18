package com.sample.pagination.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sample.pagination.api.RetrofitService
import com.sample.pagination.model.Movie

class MoviePagingSource(private val apiService: RetrofitService):
PagingSource<Int, Movie>(){
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let {
            anchorPosition -> state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)?:
            state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try{
            val position = params.key?:1
            val response = apiService.getTopRatedMovies("adbeaa9fb1f9291f02db2009f878b378","en-US",position)
            LoadResult.Page(data = response.body()!!.results,
            prevKey = if(position == 1) null else position-1,
            nextKey = position+1)
        }catch (e:Exception){
            LoadResult.Error(e)
        }
    }
}