package com.yasincidem.moviedb.feature.trending.data.remote.model

import androidx.annotation.Keep
import com.yasincidem.moviedb.core.model.MediaType

@Keep
data class TrendingItemDto(
    val id: Long?,
    val adult: Boolean?,
    val backdrop_path: String?,
    val name: String?,
    val title: String?,
    val original_language: String?,
    val original_name: String?,
    val overview: String?,
    val poster_path: String?,
    val profile_path: String?,
    val media_type: MediaType,
    val genre_ids: List<Int?>?,
    val popularity: Double?,
    val first_air_date: String?,
    val vote_average: Double?,
    val vote_count: Int?,
    val origin_country: List<String?>?,
    val gender: Int?,
    val known_for_department: String?,
    val known_for: List<TrendingItemDto?>?
)