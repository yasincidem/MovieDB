package com.yasincidem.moviedb.feature.moviedetail.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yasincidem.moviedb.extensions.onEachResource
import com.yasincidem.moviedb.extensions.safeLaunchIn
import com.yasincidem.moviedb.feature.moviedetail.domain.model.MOVIE_DETAIL_DEFAULT
import com.yasincidem.moviedb.feature.moviedetail.domain.usecase.FetchMovieDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val fetchMovieDetailUseCase: FetchMovieDetailUseCase
) : ViewModel() {

    private val _movieDetail = MutableStateFlow(MOVIE_DETAIL_DEFAULT)
    val movieDetail = _movieDetail.asStateFlow()

    fun fetchMovieDetail(movieId: Long) {
        fetchMovieDetailUseCase.fetchMovieDetail(movieId).onEachResource(
            onSuccess = { movieDetail ->
                _movieDetail.update { movieDetail }
            }
        ).safeLaunchIn(viewModelScope)
    }
}