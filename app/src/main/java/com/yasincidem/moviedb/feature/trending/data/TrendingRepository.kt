package com.yasincidem.moviedb.feature.trending.data

import com.yasincidem.moviedb.feature.trending.data.remote.TrendingService
import com.yasincidem.moviedb.feature.trending.domain.model.TrendingConfig
import javax.inject.Inject
import kotlinx.coroutines.flow.flow

class TrendingRepository @Inject constructor(
    private val service: TrendingService
) {

    fun fetchTrending(trendingConfig: TrendingConfig) = flow {
        emit(service.fetchTrending(trendingConfig.mediaType, trendingConfig.timeWindow))
    }
}