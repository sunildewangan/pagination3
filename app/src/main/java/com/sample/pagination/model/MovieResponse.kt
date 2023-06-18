package com.sample.pagination.model

data class MovieResponse(
    val page: Int, val results: List<Movie>
)
