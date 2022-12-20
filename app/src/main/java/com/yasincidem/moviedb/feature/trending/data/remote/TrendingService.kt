package com.yasincidem.moviedb.feature.trending.data.remote

import com.yasincidem.moviedb.feature.trending.domain.model.MediaType
import com.yasincidem.moviedb.feature.trending.domain.model.TimeWindow
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path

interface TrendingService {

    @GET("trending/{mediaType}/{timeWindow}")
    suspend fun fetchTrending(
        @Path("mediaType") mediaType: MediaType,
        @Path("timeWindow") timeWindow: TimeWindow
    ): ResponseBody
}