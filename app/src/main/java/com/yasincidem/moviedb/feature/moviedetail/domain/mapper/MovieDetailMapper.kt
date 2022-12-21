package com.yasincidem.moviedb.feature.moviedetail.domain.mapper

import com.yasincidem.moviedb.extensions.orZero
import com.yasincidem.moviedb.feature.moviedetail.data.remote.model.MovieDetailDto
import com.yasincidem.moviedb.feature.moviedetail.domain.model.MovieDetail
import javax.inject.Inject

class MovieDetailMapper @Inject constructor() {

    fun mapToMovieDetail(dto: MovieDetailDto) = MovieDetail(
        id = dto.id.orZero(),
        homepage = dto.homepage.orEmpty(),
        original_title = dto.original_title.orEmpty(),
        title = dto.title.orEmpty(),
        overview = dto.overview.orEmpty(),
        poster_path = dto.poster_path.orEmpty(),
        runtime = dto.runtime.orEmpty(),
        vote_average = dto.vote_average.orZero(),
        vote_count = dto.vote_count.orZero()
    )
}