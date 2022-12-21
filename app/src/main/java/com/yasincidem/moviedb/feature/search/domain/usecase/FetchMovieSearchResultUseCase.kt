package com.yasincidem.moviedb.feature.search.domain.usecase

import com.yasincidem.moviedb.core.Resource
import com.yasincidem.moviedb.extensions.mapData
import com.yasincidem.moviedb.feature.search.data.MovieSearchRepository
import com.yasincidem.moviedb.feature.search.domain.mapper.MovieSearchResultMapper
import com.yasincidem.moviedb.feature.search.domain.model.MovieSearchResult
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class FetchMovieSearchResultUseCase @Inject constructor(
    private val repository: MovieSearchRepository,
    private val mapper: MovieSearchResultMapper
) {

    fun fetchSearchResult(query: String): Flow<Resource<MovieSearchResult>> =
        repository.fetchSearchResult(query).mapData(mapper::mapToMovieSearchResult)
}