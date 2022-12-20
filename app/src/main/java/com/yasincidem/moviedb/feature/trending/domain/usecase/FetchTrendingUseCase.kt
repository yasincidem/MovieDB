package com.yasincidem.moviedb.feature.trending.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.yasincidem.moviedb.feature.trending.data.TrendingRepository
import com.yasincidem.moviedb.feature.trending.data.remote.TrendingService
import com.yasincidem.moviedb.feature.trending.domain.mapper.TrendingMapper
import com.yasincidem.moviedb.feature.trending.domain.model.IMedia
import com.yasincidem.moviedb.feature.trending.domain.model.TrendingConfig
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class FetchTrendingUseCase @Inject constructor(
    private val service: TrendingService,
    private val mapper: TrendingMapper
) {

    fun fetchTrending(trendingConfig: TrendingConfig, pageSize: Int = 10): Flow<PagingData<IMedia>> = Pager(
        PagingConfig(pageSize)
    ) {
        TrendingRepository(
            service = service,
            mapper = mapper,
            trendingConfig = trendingConfig
        )
    }.flow
}