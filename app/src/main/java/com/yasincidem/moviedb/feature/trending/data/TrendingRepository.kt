package com.yasincidem.moviedb.feature.trending.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.yasincidem.moviedb.feature.trending.data.remote.TrendingService
import com.yasincidem.moviedb.feature.trending.domain.mapper.TrendingMapper
import com.yasincidem.moviedb.feature.trending.domain.model.IMedia
import com.yasincidem.moviedb.feature.trending.domain.model.TrendingConfig
import javax.inject.Inject

class TrendingRepository @Inject constructor(
    private val service: TrendingService,
    private val mapper: TrendingMapper,
    private val trendingConfig: TrendingConfig,
) : PagingSource<Int, IMedia>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, IMedia> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = service.fetchTrending(
                trendingConfig.mediaRequestType,
                trendingConfig.timeWindow,
                nextPageNumber
            )
            val mediaList = mapper.mapToMediaList(response.results)

            LoadResult.Page(
                data = mediaList,
                prevKey = null, // Only paging forward.
                nextKey = response.page?.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, IMedia>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}