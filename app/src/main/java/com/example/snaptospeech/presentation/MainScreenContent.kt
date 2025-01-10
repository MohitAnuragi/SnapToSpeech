package com.example.snaptospeech.presentation

import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat

@Composable
fun MainScreenContent(drawerIcon: () -> Unit, viewModel: OcrViewModel) {
    val context = LocalContext.current
    val photoUri = createImageUri(context = context)
    val state by viewModel.uiState.collectAsState()

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { isSuccess ->
            if (isSuccess) {
                viewModel.startExtraction(photoUri)
            } else {
                Toast.makeText(context, "Error capturing image", Toast.LENGTH_SHORT).show()
            }
        }
    )

    val getImages = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        uri?.let {
            viewModel.startExtraction(it)
        }
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            cameraLauncher.launch(photoUri)
        } else {
            Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(
        topBar = { MainScreenTopBar(drawerIcon = drawerIcon) },
        bottomBar = {
            BottomAppBar(
                cameraClick = {
                    if (checkPermission(context)) {
                        cameraLauncher.launch(photoUri)
                    } else {
                        permissionLauncher.launch(android.Manifest.permission.CAMERA)
                    }
                },
                galleryClick = {
                    getImages.launch(PickVisualMediaRequest(mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly))
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp)
        ) {
            item {
                ImageWindow(
                    image = state.image
                )
            }
        }
    }
}



fun checkPermission(context: Context): Boolean {
    val permissionChecked = ContextCompat.checkSelfPermission(context, android.Manifest.permission.CAMERA)
    return permissionChecked == PackageManager.PERMISSION_GRANTED
}
