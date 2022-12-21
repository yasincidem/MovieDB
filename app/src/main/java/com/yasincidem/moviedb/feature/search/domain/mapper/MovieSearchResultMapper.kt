package com.yasincidem.moviedb.feature.search.domain.mapper

import androidx.annotation.VisibleForTesting
import com.yasincidem.moviedb.extensions.orZero
import com.yasincidem.moviedb.feature.search.data.remote.model.MovieSearchResultDto
import com.yasincidem.moviedb.feature.search.data.remote.model.MovieSearchResultItemDto
import com.yasincidem.moviedb.feature.search.domain.model.MovieSearchResult
import com.yasincidem.moviedb.feature.search.domain.model.MovieSearchResultItem
import javax.inject.Inject

class MovieSearchResultMapper @Inject constructor() {

    fun mapToMovieSearchResult(dto: MovieSearchResultDto?) = MovieSearchResult(
        results = dto?.results?.map(this::mapToMovieSearchResultItem).orEmpty()
    )

    @VisibleForTesting
    fun mapToMovieSearchResultItem(dto: MovieSearchResultItemDto?) = MovieSearchResultItem(
        id = dto?.id.orZero(),
        title = dto?.title.orEmpty(),
        original_title = dto?.original_title.orEmpty(),
        overview = dto?.overview.orEmpty(),
        poster_path = if (dto?.poster_path.isNullOrEmpty()) "" else POSTER_BASE_PATH + dto?.poster_path.orEmpty()
    )

    companion object {
        private const val POSTER_BASE_PATH = "https://image.tmdb.org/t/p/w500/"
    }
}