package com.yasincidem.moviedb.feature.moviedetail.data.remote

import com.yasincidem.moviedb.feature.moviedetail.data.remote.model.MovieDetailDto
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieDetailService {

    @GET("movie/{movieId}")
    suspend fun fetchMovieDetail(
        @Path("movieId") movieId: Long
    ): MovieDetailDto
}