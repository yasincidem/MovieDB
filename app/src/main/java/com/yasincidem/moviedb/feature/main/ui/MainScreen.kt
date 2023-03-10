package com.yasincidem.moviedb.feature.main.ui

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.yasincidem.moviedb.feature.destinations.MovieDetailScreenDestination
import com.yasincidem.moviedb.feature.destinations.MovieSearchScreenDestination
import com.yasincidem.moviedb.feature.search.ui.DisabledSearchBar
import com.yasincidem.moviedb.feature.trending.domain.model.IMedia
import com.yasincidem.moviedb.feature.trending.domain.model.Movie
import com.yasincidem.moviedb.feature.trending.domain.model.Person
import com.yasincidem.moviedb.feature.trending.domain.model.TV
import com.yasincidem.moviedb.feature.trending.ui.MovieCard
import com.yasincidem.moviedb.feature.trending.ui.TvCard
import com.yasincidem.moviedb.perf.rememberMetricsStateHolder
import com.yasincidem.moviedb.util.FadingBox
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@RootNavGraph(start = true)
@Destination
@Composable
fun MainScreen(
    navigator: DestinationsNavigator,
    viewmodel: MainViewModel = hiltViewModel()
) {

    val trendingList = viewmodel.trendingList.collectAsLazyPagingItems()
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    val isGoUpButtonVisible by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex > 0
        }
    }
    val metricsStateHolder = rememberMetricsStateHolder()
    val systemUiController = rememberSystemUiController()
    val context = LocalContext.current

    LaunchedEffect(metricsStateHolder, listState) {
        snapshotFlow { listState.isScrollInProgress }.collect { isScrolling ->
            if (isScrolling) {
                metricsStateHolder.state?.putState("LazyList", "Scrolling")
            } else {
                metricsStateHolder.state?.removeState("LazyList")
            }
        }
    }

    DisposableEffect(systemUiController) {
        systemUiController.setStatusBarColor(
            color = Color.Transparent,
            darkIcons = true
        )
        onDispose {}
    }

    Scaffold(
        topBar = {
            Surface(
                tonalElevation = 16.dp,
                shadowElevation = 16.dp
            ) {
                TopAppBar(
                    title = {
                        DisabledSearchBar(
                            onClicked = {
                                navigator.navigate(MovieSearchScreenDestination())
                            }
                        )
                    },
                    windowInsets = WindowInsets.statusBars,
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            AnimatedVisibility(
                visible = isGoUpButtonVisible,
                enter = fadeIn(),
                exit = fadeOut(),
            ) {
                SmallFloatingActionButton(
                    modifier = Modifier.systemBarsPadding(),
                    onClick = {
                        scope.launch {
                            listState.animateScrollToItem(0)
                        }
                    },
                    elevation = FloatingActionButtonDefaults.loweredElevation()
                ) {
                    Icon(imageVector = Icons.Outlined.KeyboardArrowUp, contentDescription = "Go up")
                }
            }
        }
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            contentPadding = PaddingValues(
                start = 16.dp,
                end = 16.dp,
                top = padding.calculateTopPadding(),
                bottom = padding.calculateBottomPadding()
            ),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            state = listState
        ) {
            items(trendingList) { media: IMedia? ->
                media ?: return@items
                FadingBox {
                    Card(
                        modifier = Modifier,
                        onClick = {
                            when (media) {
                                is Movie -> {
                                    navigator.navigate(
                                        MovieDetailScreenDestination(
                                            media.id,
                                            media.backdrop_path
                                        )
                                    )
                                }

                                is Person, is TV -> {
                                    // todo(Implement Later)
                                    Toast.makeText(
                                        context,
                                        "Person and TV detail pages does not exist yet",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                        },
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        when (media) {
                            is Movie -> {
                                MovieCard(media)
                            }

                            is TV -> {
                                TvCard(media)
                            }

                            is Person -> {
                                // todo(Implement Later)
                            }
                        }
                    }
                }
            }
        }
    }
}
