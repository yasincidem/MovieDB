package com.yasincidem.moviedb.feature.main.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.yasincidem.moviedb.feature.trending.domain.model.MediaType
import com.yasincidem.moviedb.feature.trending.domain.model.TimeWindow
import com.yasincidem.moviedb.feature.trending.domain.model.TrendingConfig
import com.yasincidem.moviedb.feature.trending.ui.TrendingViewModel

@RootNavGraph(start = true)
@Destination
@Composable
fun MainScreen(
    navigator: DestinationsNavigator,
    viewmodel: TrendingViewModel = hiltViewModel()
) {

    LaunchedEffect(Unit) {
        viewmodel.fetchTrending(TrendingConfig(MediaType.movie, TimeWindow.week))
    }
}