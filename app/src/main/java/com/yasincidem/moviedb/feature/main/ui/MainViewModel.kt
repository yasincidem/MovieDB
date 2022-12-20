package com.yasincidem.moviedb.feature.main.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.yasincidem.moviedb.feature.trending.domain.model.IMedia
import com.yasincidem.moviedb.feature.trending.domain.model.MediaRequestType
import com.yasincidem.moviedb.feature.trending.domain.model.TimeWindow
import com.yasincidem.moviedb.feature.trending.domain.model.TrendingConfig
import com.yasincidem.moviedb.feature.trending.domain.usecase.FetchTrendingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

@HiltViewModel
class MainViewModel @Inject constructor(
    private val fetchTrendingUseCase: FetchTrendingUseCase
) : ViewModel() {

    private val trendingConfig = TrendingConfig(MediaRequestType.all, TimeWindow.week)

    val trendingList: Flow<PagingData<IMedia>> =
        fetchTrendingUseCase.fetchTrending(trendingConfig).cachedIn(viewModelScope)
}