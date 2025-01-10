package com.example.snaptospeech.data

import android.content.Context
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.devanagari.DevanagariTextRecognizerOptions

interface AppContainer {
    val ocrRepository: OcrRepositoryImpl
}

class AppContainerImpl(
    private val context: Context
): AppContainer {

    // Dependency Injection
    override val ocrRepository: OcrRepositoryImpl by lazy {
        OcrRepositoryImpl(
            context = context,
            textRecognizer = TextRecognition.getClient(
                DevanagariTextRecognizerOptions.Builder().build()
            )
        )
    }
}
