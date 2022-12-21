package com.yasincidem.moviedb.util

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun FadingBox(
    enterDuration: Int = 550,
    exitDuration: Int = 550,
    content: @Composable () -> Unit
) {
    val state = remember {
        MutableTransitionState(false).apply {
            // Start the animation immediately.
            targetState = true
        }
    }

    AnimatedVisibility(
        visibleState = state,
        enter = fadeIn(animationSpec = tween(enterDuration)),
        exit = fadeOut(animationSpec = tween(exitDuration))
    ) { content() }
}