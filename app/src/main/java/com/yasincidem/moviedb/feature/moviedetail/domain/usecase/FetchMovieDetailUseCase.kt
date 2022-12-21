package com.yasincidem.moviedb.feature.moviedetail.domain.usecase

import com.yasincidem.moviedb.core.Resource
import com.yasincidem.moviedb.extensions.mapData
import com.yasincidem.moviedb.feature.moviedetail.data.MovieDetailRepository
import com.yasincidem.moviedb.feature.moviedetail.domain.mapper.MovieDetailMapper
import com.yasincidem.moviedb.feature.moviedetail.domain.model.MovieDetail
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class FetchMovieDetailUseCase @Inject constructor(
    private val repository: MovieDetailRepository,
    private val mapper: MovieDetailMapper
) {
    fun fetchMovieDetail(movieId: Long): Flow<Resource<MovieDetail>> =
        repository.fetchMovieDetail(movieId).mapData(mapper::mapToMovieDetail)
}