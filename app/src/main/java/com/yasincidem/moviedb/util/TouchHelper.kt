package com.yasincidem.moviedb.util

import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput

fun Modifier.disableMultiTouch(): Modifier = pointerInput(Unit) {
    awaitPointerEventScope {
        while (true) {
            awaitPointerEvent(PointerEventPass.Initial).changes.forEachIndexed { index, change ->
                if (index > 0) {
                    with(change) {
                        if (pressed != previousPressed) consume()
                    }
                }
            }
        }
    }
}