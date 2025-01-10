package com.example.snaptospeech.data

import android.content.Context
import android.net.Uri
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognizer
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import java.util.*

interface OcrRepository {
    fun extractTextFromImage(uri: Uri): Flow<String>
    fun stopSpeech()
    fun startSpeech(text: String)
    fun resumeSpeech()
    fun toggleSpeech()
}

class OcrRepositoryImpl(
    private val context: Context,
    private val textRecognizer: TextRecognizer
) : OcrRepository {

    private var tts: TextToSpeech? = null
    private var isInitialized by mutableStateOf(false)
    private var lastText: String? = null
    private var isSpeaking by mutableStateOf(false)

    init {
        tts = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                tts?.language = Locale("hi", "IN") // Set language to Hindi
                tts?.setSpeechRate(0.9f)
                isInitialized = true
            } else {
                Log.e("TTS", "Initialization failed")
            }
        }
    }

    override fun extractTextFromImage(uri: Uri): Flow<String> {
        return callbackFlow {
            val inputImage = InputImage.fromFilePath(context, uri)
            textRecognizer.process(inputImage)
                .addOnSuccessListener { visionText ->
                    val extractedText = visionText.text
                    launch {
                        send(extractedText)
                        if (isInitialized) {
                            lastText = extractedText
                            startSpeech(extractedText)
                        } else {
                            Log.e("TTS", "Text-to-Speech not initialized")
                        }
                    }
                }
                .addOnFailureListener { exception ->
                    Log.e("OCR", "extractTextFromImage: ${exception.message}")
                }
            awaitClose {
                tts?.stop()
                tts?.shutdown()
            }
        }
    }

    override fun stopSpeech() {
        tts?.stop()
        isSpeaking = false
    }

    override fun startSpeech(text: String) {
        lastText = text
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
        isSpeaking = true
    }

    override fun resumeSpeech() {
        lastText?.let { startSpeech(it) }
    }

    override fun toggleSpeech() {
        if (isSpeaking) {
            stopSpeech()
        } else {
            resumeSpeech()
        }
    }
}
