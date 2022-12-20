package com.yasincidem.moviedb.feature.trending.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yasincidem.moviedb.feature.trending.domain.model.TrendingConfig
import com.yasincidem.moviedb.feature.trending.domain.usecase.FetchTrendingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class TrendingViewModel @Inject constructor(
    private val fetchTrendingUseCase: FetchTrendingUseCase
) : ViewModel() {

    fun fetchTrending(trendingConfig: TrendingConfig) = viewModelScope.launch {
        fetchTrendingUseCase.fetchTrending(trendingConfig).collect {

        }
    }
}