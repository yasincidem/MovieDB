package com.yasincidem.moviedb.feature.trending.domain.model

data class TrendingConfig(
    val mediaRequestType: MediaRequestType,
    val timeWindow: TimeWindow
)