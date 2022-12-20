package com.yasincidem.moviedb.core.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
enum class MediaType : Parcelable {
    movie,
    tv,
    person,
}