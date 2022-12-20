package com.yasincidem.moviedb.extensions

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import androidx.compose.ui.graphics.Color
import androidx.palette.graphics.Palette
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult

suspend fun String.getPaletteOrNull(context: Context): Palette? {
    val loader = ImageLoader(context)
    val request = ImageRequest.Builder(context)
        .data(this)
        .allowHardware(false) // Disable hardware bitmaps.
        .build()

    val result = (loader.execute(request) as? SuccessResult)?.drawable
    val bitmap = (result as? BitmapDrawable)?.bitmap

    return if (bitmap == null) {
        null
    } else {
        return try {
            Palette.from(bitmap).generate()
        } catch (ex: Exception) {
            null
        }
    }
}

fun Palette.Swatch?.asComposeColor() = Color(this?.rgb ?: 0)
