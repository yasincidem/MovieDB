package com.yasincidem.moviedb.feature.search.domain.model

data class MovieSearchResultItem(
    val id: Long,
    val title: String,
    val original_title: String,
    val overview: String,
    val poster_path: String
)

val MOVIE_SEARCH_RESULT_ITEM_DEFAULT = MovieSearchResultItem(
    id = 0,
    title = "",
    original_title = "",
    overview = "",
    poster_path = ""
)