package com.yasincidem.moviedb.feature.tvdetail.ui

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.palette.graphics.Palette
import coil.compose.AsyncImage
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ramcosta.composedestinations.annotation.Destination
import com.yasincidem.moviedb.extensions.asComposeColor
import com.yasincidem.moviedb.extensions.getPaletteOrNull
import com.yasincidem.moviedb.ui.theme.Translucent

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun TvDetailScreen(
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
            animationSpec = tween(1000)
        )
    }

    SideEffect {
        systemUiController.setStatusBarColor(
            color = Translucent,
            darkIcons = true
        )
    }

    Scaffold(
        containerColor = animatedColor.value
    ) { padding ->
        AsyncImage(
            model = backdrop_path,
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth()
                .padding(top = padding.calculateTopPadding())
        )
    }
}