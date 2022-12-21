package com.yasincidem.moviedb.feature.search.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisabledSearchBar(
    onClicked: () -> Unit
) {

    BasicTextField(
        modifier = Modifier.padding(end = 16.dp),
        value = "\uD83C\uDF7F Type a movie title",
        onValueChange = { },
        singleLine = true,
        enabled = false,
        readOnly = true,
        textStyle = TextStyle(color = Color.Gray)
    ) { innerTextField ->

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(32.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            onClick = {
                onClicked()
            }
        ) {
            Box(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .padding(8.dp),
            ) {
                innerTextField()
            }
        }
    }
}