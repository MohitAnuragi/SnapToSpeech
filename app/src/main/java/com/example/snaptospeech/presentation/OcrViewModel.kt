package com.example.snaptospeech.presentation

import android.net.Uri
import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.snaptospeech.OcrApplication
import com.example.snaptospeech.data.OcrRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OcrViewModel(
    private val repository: OcrRepositoryImpl
): ViewModel() {

    private val _uiState = MutableStateFlow(State())
    val uiState = _uiState.asStateFlow()


    fun startExtraction(image: Uri?) {
        if (image == null) return
        _uiState.update {
            it.copy(
                image = image,
                text = ""
            )
        }
        viewModelScope.launch {
            repository.extractTextFromImage(image).collect { generatedText ->
                _uiState.update {
                    it.copy(
                        text = uiState.value.text + generatedText
                    )
                }
            }
        }
    }
    fun resumeSpeech() {
        repository.resumeSpeech()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as OcrApplication)
                val ocrRepository = application.appContainer.ocrRepository
                OcrViewModel(repository = ocrRepository)
            }
        }
    }
}

@Stable
data class State(
    val image: Uri? = null,
    val text: String? = null,
)


