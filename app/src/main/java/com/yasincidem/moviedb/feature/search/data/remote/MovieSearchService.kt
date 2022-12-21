package com.yasincidem.moviedb.feature.search.data.remote

import com.yasincidem.moviedb.feature.search.data.remote.model.MovieSearchResultDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieSearchService {

    @GET("search/movie")
    suspend fun fetchSearchResult(
        @Query("query") query: String
    ): MovieSearchResultDto
}