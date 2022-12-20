package com.yasincidem.moviedb.feature.main.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
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
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.yasincidem.moviedb.feature.trending.domain.model.IMedia
import com.yasincidem.moviedb.feature.trending.domain.model.Movie
import com.yasincidem.moviedb.feature.trending.domain.model.Person
import com.yasincidem.moviedb.feature.trending.domain.model.TV
import com.yasincidem.moviedb.feature.trending.ui.TrendingViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@RootNavGraph(start = true)
@Destination
@Composable
fun MainScreen(
    navigator: DestinationsNavigator,
    viewmodel: TrendingViewModel = hiltViewModel()
) {

    val trendingList = viewmodel.trendingList.collectAsLazyPagingItems()
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    val isGoUpButtonVisible by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex > 0
        }
    }

    Scaffold(
        topBar = {
            Surface(
                tonalElevation = 16.dp,
                shadowElevation = 16.dp
            ) {
                TopAppBar(
                    title = {
                        Text(text = "\uD83C\uDF7F Welcome")
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
                .padding(top = 32.dp),
            contentPadding = PaddingValues(
                start = 24.dp,
                end = 24.dp,
                top = padding.calculateTopPadding()
            ),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            state = listState
        ) {
            items(trendingList, key = { item -> item.id }) { media: IMedia? ->
                media ?: return@items
                Card(
                    modifier = Modifier,
                    onClick = { /*TODO*/ },
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
                            Text(text = "person::${media.name}")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MovieCard(
    movie: Movie
) {
    Card(
        shape = RoundedCornerShape(16.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(movie.poster_path)
                .crossfade(true)
                .build(),
            contentDescription = movie.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth(0.3f)
        )
    }
}

@Composable
fun TvCard(
    tv: TV
) {
    Card(
        shape = RoundedCornerShape(16.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(tv.poster_path)
                .crossfade(true)
                .build(),
            contentDescription = tv.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth(0.3f)
        )
    }
}