package com.yasincidem.moviedb.feature.search.ui

import com.yasincidem.moviedb.feature.search.domain.model.MOVIE_SEARCH_RESULT_ITEM_DEFAULT
import com.yasincidem.moviedb.feature.search.domain.model.MovieSearchResultItem

data class MovieSearchResultItemViewState(
    val data: MovieSearchResultItem = MOVIE_SEARCH_RESULT_ITEM_DEFAULT
) {

    fun isItemVisible() = data.poster_path.isNotEmpty()
}