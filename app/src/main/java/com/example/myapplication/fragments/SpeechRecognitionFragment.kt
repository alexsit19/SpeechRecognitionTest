package com.example.myapplication.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.BaseFragment
import com.example.myapplication.MyRecognitionListener
import com.example.myapplication.databinding.FragmentSpeechRecognitionBinding
import java.util.Locale

class SpeechRecognitionFragment : BaseFragment<FragmentSpeechRecognitionBinding>() {

    private var _speechRecognizer: SpeechRecognizer? = null
    private val speechRecognizer get() = requireNotNull(_speechRecognizer)
    private val speechRecognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
    private lateinit var myRecognitionListener: MyRecognitionListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        _speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context)

        speechRecognizerIntent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        speechRecognizerIntent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE,
            Locale.getDefault().toString()
        )
    }

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentSpeechRecognitionBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationOnClickListener { buttonListener.navigateToMainFragment() }

        myRecognitionListener = MyRecognitionListener(
            onBeginningOfSpeech = {
                binding.editText.setText("")
                binding.editText.hint = "Listening..."
            },
            onResults = { bundle ->
                val data = bundle?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                binding.editText.setText(data?.get(0))
            }
        )

        speechRecognizer.setRecognitionListener(myRecognitionListener.listener)

        binding.startRecordBtn.setOnClickListener {
            Log.d("DEBUG", "Start record pressed")
            speechRecognizer.startListening(speechRecognizerIntent)
        }

        binding.stopRecordBtn.setOnClickListener {
            Log.d("DEBUG", "Stop record pressed")
            speechRecognizer.stopListening()
        }

//        binding.startRecordBtn.setOnTouchListener { v, event ->
//            when (event?.action) {
//                MotionEvent.ACTION_DOWN -> speechRecognizer.startListening(speechRecognizerIntent)
//                MotionEvent.ACTION_UP -> speechRecognizer.stopListening()
//            }
//
//            v?.onTouchEvent(event) ?: true
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        speechRecognizer.stopListening()
        speechRecognizer.destroy()
    }

    companion object {}
}