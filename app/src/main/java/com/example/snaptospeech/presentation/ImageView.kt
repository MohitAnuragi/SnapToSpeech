package com.example.snaptospeech.presentation

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.layout.ContentScale

@Composable
fun ImageWindow(image: Uri?) {
    val context = LocalContext.current
    val bitmap: ImageBitmap? = image?.let { loadBitmapFromUri(it, context) }

    bitmap?.let {
        Image(
            bitmap = it,
            contentDescription = null,
            contentScale = ContentScale.FillWidth // Adjust according to your needs
        )
    }
}

private fun loadBitmapFromUri(uri: Uri, context: Context): ImageBitmap? {
    return try {
        context.contentResolver.openInputStream(uri)?.use { inputStream ->
            val bitmap = BitmapFactory.decodeStream(inputStream)
            bitmap?.asImageBitmap()
        }
    } catch (e: Exception) {
        // Handle exceptions, such as IOException or SecurityException
        null
    }
}
