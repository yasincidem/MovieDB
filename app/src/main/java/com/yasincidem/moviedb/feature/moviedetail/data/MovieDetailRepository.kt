package com.yasincidem.moviedb.feature.moviedetail.data

import com.yasincidem.moviedb.extensions.remote
import com.yasincidem.moviedb.feature.moviedetail.data.remote.MovieDetailService
import javax.inject.Inject
import kotlinx.coroutines.flow.flow

class MovieDetailRepository @Inject constructor(
    private val service: MovieDetailService
) {

    fun fetchMovieDetail(movieId: Long) = flow {
        emit(service.fetchMovieDetail(movieId))
    }.remote()
}