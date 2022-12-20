package com.yasincidem.moviedb.feature.trending.data.remote

import com.yasincidem.moviedb.feature.trending.data.remote.model.TrendingPageDto
import com.yasincidem.moviedb.feature.trending.domain.model.MediaRequestType
import com.yasincidem.moviedb.feature.trending.domain.model.TimeWindow
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TrendingService {

    @GET("trending/{mediaType}/{timeWindow}")
    suspend fun fetchTrending(
        @Path("mediaType") mediaRequestType: MediaRequestType,
        @Path("timeWindow") timeWindow: TimeWindow,
        @Query("page") page: Int
    ): TrendingPageDto
}