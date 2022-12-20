package com.yasincidem.moviedb.feature.trending.data.remote.model

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class TrendingPageDto(
    val page: Int?,
    val results: List<TrendingItemDto>,
    @Json(name = "total_pages")
    val totalPageNum: Int?,
    @Json(name = "total_results")
    val totalResultNum: Int?
)