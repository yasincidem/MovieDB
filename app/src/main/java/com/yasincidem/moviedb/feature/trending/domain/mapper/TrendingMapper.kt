package com.yasincidem.moviedb.feature.trending.domain.mapper

import androidx.annotation.VisibleForTesting
import com.yasincidem.moviedb.extensions.orFalse
import com.yasincidem.moviedb.extensions.orZero
import com.yasincidem.moviedb.core.model.MediaType
import com.yasincidem.moviedb.feature.trending.data.remote.model.TrendingItemDto
import com.yasincidem.moviedb.feature.trending.domain.model.IMedia
import com.yasincidem.moviedb.feature.trending.domain.model.Movie
import com.yasincidem.moviedb.feature.trending.domain.model.Person
import com.yasincidem.moviedb.feature.trending.domain.model.TV
import javax.inject.Inject

class TrendingMapper @Inject constructor() {

    fun mapToMediaList(dto: List<TrendingItemDto>): List<IMedia> = dto.map(this::mapToMedia)

    @VisibleForTesting
    fun mapToMedia(dto: TrendingItemDto): IMedia = when (dto.media_type) {
        MediaType.movie -> mapToMovie(dto)
        MediaType.tv -> mapToTV(dto)
        MediaType.person -> mapToPerson(dto)
    }

    @VisibleForTesting
    fun mapToMovie(dto: TrendingItemDto) = Movie(
        id = dto.id.orZero(),
        adult = dto.adult.orFalse(),
        backdrop_path = BACKDROP_BASE_PATH + dto.backdrop_path.orEmpty(),
        title = dto.title.orEmpty(),
        original_language = dto.original_language.orEmpty(),
        original_name = dto.original_name.orEmpty(),
        overview = dto.overview.orEmpty(),
        poster_path = POSTER_BASE_PATH +  dto.poster_path.orEmpty(),
        genre_ids = listOf(),
        popularity = dto.popularity.orZero(),
        first_air_date = dto.first_air_date.orEmpty(),
        vote_average = dto.vote_average.orZero(),
        vote_count = dto.vote_count.orZero(),
        origin_country = listOf(),
    )

    @VisibleForTesting
    fun mapToTV(dto: TrendingItemDto) = TV(
        id = dto.id.orZero(),
        adult = dto.adult.orFalse(),
        backdrop_path = BACKDROP_BASE_PATH + dto.backdrop_path.orEmpty(),
        name = dto.name.orEmpty(),
        original_language = dto.original_language.orEmpty(),
        original_name = dto.original_name.orEmpty(),
        overview = dto.overview.orEmpty(),
        poster_path = POSTER_BASE_PATH + dto.poster_path.orEmpty(),
        genre_ids = listOf(),
        popularity = dto.popularity.orZero(),
        first_air_date = dto.first_air_date.orEmpty(),
        vote_average = dto.vote_average.orZero(),
        vote_count = dto.vote_count.orZero(),
        origin_country = listOf(),
    )

    @VisibleForTesting
    fun mapToPerson(dto: TrendingItemDto) = Person(
        id = dto.id.orZero(),
        adult = dto.adult.orFalse(),
        backdrop_path = BACKDROP_BASE_PATH + dto.backdrop_path.orEmpty(),
        name = dto.name.orEmpty(),
        original_language = dto.original_language.orEmpty(),
        original_name = dto.original_name.orEmpty(),
        overview = dto.overview.orEmpty(),
        profile_path = POSTER_BASE_PATH + dto.profile_path.orEmpty(),
        genre_ids = listOf(),
        popularity = dto.popularity.orZero(),
        first_air_date = dto.first_air_date.orEmpty(),
        vote_average = dto.vote_average.orZero(),
        vote_count = dto.vote_count.orZero(),
        origin_country = listOf(),
        gender = dto.gender.orZero(),
        known_for_department = dto.known_for_department.orEmpty(),
    )

    companion object {
        private const val POSTER_BASE_PATH = "https://image.tmdb.org/t/p/w500/"
        private const val BACKDROP_BASE_PATH = "https://image.tmdb.org/t/p/w500/"
    }
}