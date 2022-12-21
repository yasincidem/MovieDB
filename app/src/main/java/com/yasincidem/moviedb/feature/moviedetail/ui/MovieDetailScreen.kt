package com.yasincidem.moviedb.feature.moviedetail.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
    val movieDetailViewState by viewModel.movieDetailViewState.collectAsStateWithLifecycle()

    val browserIntent = remember {
        derivedStateOf {
            Intent(Intent.ACTION_VIEW, Uri.parse(movieDetailViewState.data.homepage))
        }
    }

    val shareIntent = remember {
        derivedStateOf {
            Intent.createChooser(Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(
                    Intent.EXTRA_TEXT,
                    movieDetailViewState.getShareText()
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

    Surface(color = animatedColor.value) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

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


                Row(
                    modifier = Modifier.align(Alignment.BottomEnd)
                ) {
                    if (movieDetailViewState.isOpenInBrowserButtonVisible()) {
                        FilledIconButton(
                            modifier = Modifier
                                .padding(8.dp),
                            onClick = {
                                context.startActivity(browserIntent.value)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Search,
                                contentDescription = movieDetailViewState.getOpenInBrowserButtonContentDescription()
                            )
                        }
                    }

                    if (movieDetailViewState.isShareButtonVisible()) {
                        FilledIconButton(
                            modifier = Modifier
                                .padding(8.dp),
                            onClick = {
                                context.startActivity(shareIntent.value)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Share,
                                contentDescription = movieDetailViewState.getShareButtonContentDescription()
                            )
                        }
                    }
                }
            }

            FadingBox(
                enterDuration = 1000
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    text = movieDetailViewState.data.title,
                    color = nonSafePalette?.darkMutedSwatch.asComposeColor(),
                    style = MaterialTheme.typography.displaySmall
                )
            }

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                text = movieDetailViewState.data.overview,
                color = nonSafePalette?.darkVibrantSwatch.asComposeColor(),
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                text = movieDetailViewState.data.runtime,
                color = nonSafePalette?.darkVibrantSwatch.asComposeColor(),
                style = MaterialTheme.typography.titleLarge
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                text = movieDetailViewState.getVoteText(),
                color = nonSafePalette?.darkVibrantSwatch.asComposeColor(),
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}
