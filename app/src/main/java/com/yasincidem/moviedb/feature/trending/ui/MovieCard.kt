package com.yasincidem.moviedb.feature.trending.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.yasincidem.moviedb.feature.trending.domain.model.Movie
import com.yasincidem.moviedb.ui.theme.Translucent

@Composable
fun MovieCard(
    movie: Movie
) {

    val context = LocalContext.current

    Card(
        shape = RoundedCornerShape(16.dp)
    ) {
        Box {

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(context).data(movie.poster_path)
                        .crossfade(true).build(),
                    contentDescription = movie.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth(0.3f)
                )

                Column(
                    modifier = Modifier.padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = movie.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = movie.overview,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 4,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .background(Translucent)
                    .padding(4.dp)
            ) {
                Text(
                    text = "Movie",
                    color = Color.White
                )
            }
        }
    }
}