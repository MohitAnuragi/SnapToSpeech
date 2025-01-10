package com.example.snaptospeech.presentation

import androidx.compose.runtime.Composable
import com.example.snaptospeech.data.OcrRepositoryImpl

@Composable
fun MainScreen(viewModel: OcrViewModel,ocrRepository: OcrRepositoryImpl) {


    MainScreenContent(
        drawerIcon = {
        }, viewModel = viewModel
    )
}


