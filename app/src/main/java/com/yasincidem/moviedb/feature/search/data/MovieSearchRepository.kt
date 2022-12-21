package com.yasincidem.moviedb.feature.search.data

import com.yasincidem.moviedb.extensions.remote
import com.yasincidem.moviedb.feature.search.data.remote.MovieSearchService
import javax.inject.Inject
import kotlinx.coroutines.flow.flow

class MovieSearchRepository @Inject constructor(
    private val service: MovieSearchService
) {

    fun fetchSearchResult(query: String) = flow {
        emit(service.fetchSearchResult(query))
    }.remote()
}