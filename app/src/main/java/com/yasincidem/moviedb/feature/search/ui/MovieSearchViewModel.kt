package com.yasincidem.moviedb.feature.search.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yasincidem.moviedb.extensions.onEachResource
import com.yasincidem.moviedb.extensions.safeLaunchIn
import com.yasincidem.moviedb.feature.search.domain.model.MovieSearchResult
import com.yasincidem.moviedb.feature.search.domain.usecase.FetchMovieSearchResultUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update

@HiltViewModel
class MovieSearchViewModel @Inject constructor(
    private val fetchMovieSearchResultUseCase: FetchMovieSearchResultUseCase
) : ViewModel() {

    private val _query = MutableStateFlow("")
    val query = _query.asStateFlow()

    private val _searchResultViewState = MutableStateFlow(listOf(MovieSearchResultItemViewState()))
    val searchResultViewState = _searchResultViewState.asStateFlow()

    private val job = _query.debounce(500)
        .distinctUntilChanged()
        .filter { it.isNotEmpty() }
        .flatMapLatest(fetchMovieSearchResultUseCase::fetchSearchResult)
        .onEachResource(onSuccess = (::updateSearchResultViewState))
        .safeLaunchIn(viewModelScope)

    private fun updateSearchResultViewState(searchResult: MovieSearchResult) {
        _searchResultViewState.update {
            searchResult.results.map {
                MovieSearchResultItemViewState(it)
            }
        }
    }

    fun updateQuery(query: String) {
        _query.update {
            query
        }
    }

    override fun onCleared() {
        job.cancel()
        super.onCleared()
    }
}