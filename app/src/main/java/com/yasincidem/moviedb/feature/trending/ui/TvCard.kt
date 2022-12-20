package com.yasincidem.moviedb.feature.trending.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.yasincidem.moviedb.feature.trending.domain.model.TV

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