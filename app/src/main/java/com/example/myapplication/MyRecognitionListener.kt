package com.example.myapplication

import android.os.Bundle
import android.speech.RecognitionListener
import android.util.Log

typealias OnBeginningOfSpeech = () -> Unit
typealias OnResults = (bundle: Bundle?) -> Unit

class MyRecognitionListener(
    private val onBeginningOfSpeech: OnBeginningOfSpeech,
    private val onResults: OnResults
) {

    val listener: RecognitionListener = object : RecognitionListener {

        override fun onReadyForSpeech(p0: Bundle?) {
            Log.d("DEBUG", "onReadyForSpeech: $p0")
        }

        override fun onBeginningOfSpeech() {
            Log.d("DEBUG", "onBeginningOfSpeech:")
            onBeginningOfSpeech.invoke()
        }

        override fun onRmsChanged(p0: Float) {
            Log.d("DEBUG", "onRmsChanged: $p0:")
        }

        override fun onBufferReceived(amount: ByteArray?) {
            Log.d("DEBUG", "onBufferReceived: ${amount?.size}")
        }

        override fun onEndOfSpeech() {
            Log.d("DEBUG", "onEndOfSpeech:")
        }

        override fun onError(error: Int) {
            Log.d("DEBUG", "onError: $error")
        }

        override fun onResults(results: Bundle?) {
            Log.d("DEBUG", "onResults:")
            onResults.invoke(results)
        }

        override fun onPartialResults(p0: Bundle?) {
            Log.d("DEBUG", "onPartialResults:")
        }

        override fun onEvent(p0: Int, p1: Bundle?) {
            Log.d("DEBUG", "onEvent: $p0, $p1")
        }
    }
}