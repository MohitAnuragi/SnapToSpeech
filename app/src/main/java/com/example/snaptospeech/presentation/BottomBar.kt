package com.example.snaptospeech.presentation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.snaptospeech.R

@Composable
fun BottomAppBar(
    cameraClick: () -> Unit = {},
    galleryClick: () -> Unit = {},
) {
    BottomAppBar(
        actions = {},
        floatingActionButton = {
            Row {
                BottomAppBarFloatingActionButton(
                    id = R.drawable.photo, // Gallery icon
                    onClick = galleryClick
                )
                Spacer(modifier = Modifier.width(190.dp))
                BottomAppBarFloatingActionButton(
                    id = R.drawable.camer, // Camera icon
                    onClick = cameraClick
                )
            }
        }
    )
}

@Composable
private fun BottomAppBarFloatingActionButton(
    @DrawableRes id: Int,
    onClick: () -> Unit = {}
) {
    FloatingActionButton(
        onClick = onClick,
        containerColor = MaterialTheme.colorScheme.primaryContainer, // Set a background color that contrasts with the icons
    ) {
        Icon(
            painter = painterResource(id = id),
            contentDescription = null, // Display the icon in its original color
        )
    }
}
