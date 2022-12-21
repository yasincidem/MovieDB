package com.yasincidem.moviedb.feature.moviedetail.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.palette.graphics.Palette
import coil.compose.AsyncImage
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.yasincidem.moviedb.extensions.asComposeColor
import com.yasincidem.moviedb.extensions.getPaletteOrNull
import com.yasincidem.moviedb.ui.theme.Translucent
import com.yasincidem.moviedb.util.FadingBox

@OptIn(ExperimentalLifecycleComposeApi::class)
@Destination
@Composable
fun MovieDetailScreen(
    id: Long,
    backdrop_path: String,
    navigator: DestinationsNavigator,
    viewModel: MovieDetailViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val systemUiController = rememberSystemUiController()
    var nonSafePalette by remember { mutableStateOf<Palette?>(null) }
    val animatedColor = remember { Animatable(Color.White) }
    val movieDetail by viewModel.movieDetail.collectAsStateWithLifecycle()

    val browserIntent = remember {
        derivedStateOf {
            Intent(Intent.ACTION_VIEW, Uri.parse(movieDetail.homepage))
        }
    }

    val shareIntent = remember {
        derivedStateOf {
            Intent.createChooser(Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(
                    Intent.EXTRA_TEXT,
                    "Here is a movie recommendation: ${movieDetail.title} \n\n ${movieDetail.homepage}"
                )
                type = "text/plain"
            }, null)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.fetchMovieDetail(id)
        val palette = backdrop_path.getPaletteOrNull(context)
        nonSafePalette = palette
        animatedColor.animateTo(
            palette?.lightMutedSwatch.asComposeColor(),
            animationSpec = tween(1200)
        )
    }

    DisposableEffect(systemUiController) {
        systemUiController.setStatusBarColor(
            color = Translucent,
            darkIcons = false
        )
        onDispose {}
    }

    Surface(
        color = animatedColor.value
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Box {

                    AsyncImage(
                        model = backdrop_path,
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(300.dp)
                            .fillMaxWidth()
                    )

                    FilledIconButton(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .statusBarsPadding()
                            .padding(8.dp),
                        onClick = { navigator.popBackStack() }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Navigate Up"
                        )
                    }

                    if (movieDetail.homepage.isNotEmpty()) {
                        Row(
                            modifier = Modifier.align(Alignment.BottomEnd)
                        ) {
                            FilledIconButton(
                                modifier = Modifier
                                    .padding(8.dp),
                                onClick = {
                                    context.startActivity(browserIntent.value)
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.Search,
                                    contentDescription = "Open ${movieDetail.title} in browser"
                                )
                            }

                            FilledIconButton(
                                modifier = Modifier
                                    .padding(8.dp),
                                onClick = {
                                    context.startActivity(shareIntent.value)
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.Share,
                                    contentDescription = "Share ${movieDetail.title}"
                                )
                            }
                        }
                    }
                }
            }

            item {
                FadingBox(
                    enterDuration = 1000
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        text = movieDetail.title,
                        color = nonSafePalette?.darkMutedSwatch.asComposeColor(),
                        style = MaterialTheme.typography.displaySmall
                    )
                }
            }

            item {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    text = movieDetail.overview,
                    color = nonSafePalette?.darkVibrantSwatch.asComposeColor(),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}