package com.yasincidem.moviedb.feature.moviedetail.ui

import com.yasincidem.moviedb.feature.moviedetail.domain.model.MOVIE_DETAIL_DEFAULT
import com.yasincidem.moviedb.feature.moviedetail.domain.model.MovieDetail

data class MovieDetailViewState(
    val data: MovieDetail = MOVIE_DETAIL_DEFAULT
) {

    fun isShareButtonVisible() = data.homepage.isNotEmpty()

    fun isOpenInBrowserButtonVisible() = data.homepage.isNotEmpty()

    fun getShareText() = "Here is a movie recommendation: ${data.title} \n\n ${data.homepage}"

    fun getOpenInBrowserButtonContentDescription() = "Open ${data.title} in browser"

    fun getShareButtonContentDescription() = "Share ${data.title}"

    fun getVoteText() = "${data.vote_average} (${data.vote_count})"
}