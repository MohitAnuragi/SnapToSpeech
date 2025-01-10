package com.example.snaptospeech


import android.app.Application
import com.example.snaptospeech.data.AppContainer
import com.example.snaptospeech.data.AppContainerImpl


class OcrApplication: Application() {

    lateinit var appContainer: AppContainer

    override fun onCreate() {
        super.onCreate()
        appContainer = AppContainerImpl(this)
    }
}