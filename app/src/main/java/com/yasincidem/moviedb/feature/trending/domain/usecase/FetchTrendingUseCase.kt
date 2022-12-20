package com.yasincidem.moviedb.feature.trending.domain.usecase

import com.yasincidem.moviedb.feature.trending.data.TrendingRepository
import com.yasincidem.moviedb.feature.trending.domain.model.TrendingConfig
import javax.inject.Inject

class FetchTrendingUseCase @Inject constructor(
    private val repository: TrendingRepository
) {

    fun fetchTrending(trendingConfig: TrendingConfig) = repository.fetchTrending(trendingConfig)
}