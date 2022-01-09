package com.stah.samplecomposepoint.component

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun PointTopBar(text:String, navigateUp: () -> Unit) {
    TopAppBar(
        title = { Text(text, color = Color.Black) },
        navigationIcon = {
            IconButton(onClick =  navigateUp )
            {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = Color.Black)
            }
        }
    )
}
