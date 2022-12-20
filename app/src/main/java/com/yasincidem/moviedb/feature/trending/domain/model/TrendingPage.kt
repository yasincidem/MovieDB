package com.yasincidem.moviedb.feature.trending.domain.model

data class TrendingPage(
    val page: Int?,
    val results: List<IMedia>,
    val totalPageNum: Int,
    val totalResultNum: Int?
)