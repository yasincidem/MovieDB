package com.yasincidem.moviedb.feature.trending.domain.model

sealed interface IMedia {
    val id: Long
    val adult: Boolean
    val backdrop_path: String
    val original_language: String
    val original_name: String
    val overview: String
    val genre_ids: List<Int>
    val popularity: Double
    val first_air_date: String
    val vote_average: Double
    val vote_count: Int
    val origin_country: List<String>
}

data class Movie(
    override val id: Long,
    override val adult: Boolean,
    override val backdrop_path: String,
    override val original_language: String,
    override val original_name: String,
    override val overview: String,
    override val genre_ids: List<Int>,
    override val popularity: Double,
    override val first_air_date: String,
    override val vote_average: Double,
    override val vote_count: Int,
    override val origin_country: List<String>,
    val title: String,
    val poster_path: String
) : IMedia

data class TV(
    override val id: Long,
    override val adult: Boolean,
    override val backdrop_path: String,
    override val original_language: String,
    override val original_name: String,
    override val overview: String,
    override val genre_ids: List<Int>,
    override val popularity: Double,
    override val first_air_date: String,
    override val vote_average: Double,
    override val vote_count: Int,
    override val origin_country: List<String>,
    val name: String,
    val poster_path: String
) : IMedia

data class Person(
    override val id: Long,
    override val adult: Boolean,
    override val backdrop_path: String,
    override val original_language: String,
    override val original_name: String,
    override val overview: String,
    override val genre_ids: List<Int>,
    override val popularity: Double,
    override val first_air_date: String,
    override val vote_average: Double,
    override val vote_count: Int,
    override val origin_country: List<String>,
    val name: String,
    val profile_path: String,
    val gender: Int,
    val known_for_department: String
) : IMedia