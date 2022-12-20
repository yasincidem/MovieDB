package com.yasincidem.moviedb.feature.main.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.yasincidem.moviedb.feature.trending.domain.model.IMedia
import com.yasincidem.moviedb.feature.trending.domain.model.MediaRequestType
import com.yasincidem.moviedb.feature.trending.domain.model.Movie
import com.yasincidem.moviedb.feature.trending.domain.model.Person
import com.yasincidem.moviedb.feature.trending.domain.model.TV
import com.yasincidem.moviedb.feature.trending.domain.model.TimeWindow
import com.yasincidem.moviedb.feature.trending.domain.model.TrendingConfig
import com.yasincidem.moviedb.feature.trending.ui.TrendingViewModel

@OptIn(ExperimentalMaterial3Api::class)
@RootNavGraph(start = true)
@Destination
@Composable
fun MainScreen(
    navigator: DestinationsNavigator,
    viewmodel: TrendingViewModel = hiltViewModel()
) {

    val trendingList = viewmodel.trendingList.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            Surface(
                tonalElevation = 16.dp,
                shadowElevation = 16.dp
            ) {
                TopAppBar(
                    title = {

                    },
                    windowInsets = WindowInsets.statusBars,
                )
            }
        }
    ) { padding ->
        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            contentPadding = padding,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            items(trendingList, key = { item -> item.id }) { media: IMedia? ->
                media ?: return@items
                Log.i("rereerer", media.toString())
                when (media) {
                    is Movie -> {
                        Text(text = "movie::${media.title}")
                    }

                    is TV -> {
                        Text(text = "TV::${media.name}")
                    }

                    is Person -> {
                        Text(text = "person::${media.name}")
                    }
                }
            }
        }
    }
}