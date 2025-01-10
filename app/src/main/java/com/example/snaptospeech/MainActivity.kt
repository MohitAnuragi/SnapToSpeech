package com.example.snaptospeech

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import android.view.KeyEvent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.snaptospeech.data.OcrRepositoryImpl
import com.example.snaptospeech.presentation.MainScreen
import com.example.snaptospeech.presentation.OcrViewModel
import com.example.snaptospeech.ui.theme.SnapToSpeechTheme
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.devanagari.DevanagariTextRecognizerOptions

class MainActivity : ComponentActivity() {
    private var volumeUpPressed = false
    private var volumeDownPressed = false
    private var isSpeaking = false
    private lateinit var ocrRepository: OcrRepositoryImpl
    private lateinit var ocrViewModel: OcrViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val ocrRepository = OcrRepositoryImpl(
            context = this,
            textRecognizer = TextRecognition.getClient(
                DevanagariTextRecognizerOptions.Builder().build()
            )
        )

        enableEdgeToEdge()
        setContent {
            SnapToSpeechTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(viewModel = viewModel(factory = OcrViewModel.Factory), ocrRepository = ocrRepository)
                }
            }
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        when (keyCode) {
            KeyEvent.KEYCODE_VOLUME_UP -> {
                volumeUpPressed = true
                if (volumeDownPressed) {
                    toggleSpeech()
                }
            }
            KeyEvent.KEYCODE_VOLUME_DOWN -> {
                volumeDownPressed = true
                if (volumeUpPressed) {
                    toggleSpeech()
                }
            }
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        when (keyCode) {
            KeyEvent.KEYCODE_VOLUME_UP -> volumeUpPressed = false
            KeyEvent.KEYCODE_VOLUME_DOWN -> volumeDownPressed = false
        }
        return super.onKeyUp(keyCode, event)
    }

    private fun toggleSpeech() {
        if (isSpeaking) {
            ocrRepository.stopSpeech()
            // isSpeaking = false
        } else {
            ocrViewModel.resumeSpeech() // Implement resumeSpeech in OcrViewModel to restart TTS
            //isSpeaking = true
        }
        isSpeaking = !isSpeaking
    }

}

