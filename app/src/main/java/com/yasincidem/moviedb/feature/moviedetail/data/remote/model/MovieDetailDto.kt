package com.yasincidem.moviedb.feature.moviedetail.data.remote.model

import androidx.annotation.Keep

@Keep
data class MovieDetailDto(
    val id: Long?,
    val homepage: String?,
    val original_title: String?,
    val title: String?,
    val overview: String?,
    val poster_path: String?,
    val runtime: String?,
    val vote_average: Double?,
    val vote_count: Double?
)