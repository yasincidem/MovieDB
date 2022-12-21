package com.yasincidem.moviedb.feature.search.data.remote.model

import androidx.annotation.Keep

@Keep
data class MovieSearchResultDto(
    val results: List<MovieSearchResultItemDto?>?
)