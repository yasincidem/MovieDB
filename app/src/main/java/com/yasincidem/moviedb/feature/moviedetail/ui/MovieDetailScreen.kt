package com.yasincidem.moviedb.feature.moviedetail.ui

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ramcosta.composedestinations.annotation.Destination
import com.yasincidem.moviedb.extensions.asComposeColor
import com.yasincidem.moviedb.extensions.getPaletteOrNull
import com.yasincidem.moviedb.ui.theme.Translucent

@Destination
@Composable
fun MovieDetailScreen(
    id: Long,
    poster_path: String,
    backdrop_path: String
) {
    val context = LocalContext.current
    val systemUiController = rememberSystemUiController()
    val animatedColor = remember { Animatable(Color.White) }

    LaunchedEffect(Unit) {
        val palette = backdrop_path.getPaletteOrNull(context)
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
            modifier = Modifier
                .fillMaxSize()
        ) {
            item {
                AsyncImage(
                    model = backdrop_path,
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(300.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}