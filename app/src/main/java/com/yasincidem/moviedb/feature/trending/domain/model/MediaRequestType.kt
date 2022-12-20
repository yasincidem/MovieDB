package com.yasincidem.moviedb.feature.trending.domain.model

import androidx.annotation.Keep

@Keep
enum class MediaRequestType {
    all,
    movie,
    tv,
    person,
}