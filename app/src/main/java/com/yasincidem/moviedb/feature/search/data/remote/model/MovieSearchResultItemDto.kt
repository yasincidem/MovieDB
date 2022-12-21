package com.yasincidem.moviedb.feature.search.data.remote.model

import androidx.annotation.Keep

@Keep
data class MovieSearchResultItemDto(
    val id: Long?,
    val title: String?,
    val original_title: String?,
    val overview: String?,
    val poster_path: String?
)