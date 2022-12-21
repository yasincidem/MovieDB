package com.yasincidem.moviedb.feature.moviedetail.domain.model

data class MovieDetail(
    val id: Long,
    val homepage: String,
    val original_title: String,
    val title: String,
    val overview: String,
    val poster_path: String,
    val runtime: String,
    val vote_average: Double,
    val vote_count: Double
)

val MOVIE_DETAIL_DEFAULT = MovieDetail(
    id = 0,
    homepage = "",
    original_title = "",
    title = "",
    overview = "",
    poster_path = "",
    runtime = "",
    vote_average = 0.0,
    vote_count = 0.0
)