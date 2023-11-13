package com.example.myapplication.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.ButtonListener
import com.example.myapplication.MainActivity
import com.example.myapplication.databinding.FragmentSpeechRecognitionBinding
import java.util.Locale


class SpeechRecognitionFragment : Fragment() {

    private var _binding: FragmentSpeechRecognitionBinding? = null
    private val binding get() = requireNotNull(_binding)
    private var buttonListener: ButtonListener? = null
    private var _speechRecognizer: SpeechRecognizer? = null
    private val speechRecognizer get() = requireNotNull(_speechRecognizer)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        buttonListener = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSpeechRecognitionBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setNavigationOnClickListener {
            buttonListener?.navigateToMainFragment()

            }
        _speechRecognizer = SpeechRecognizer.createSpeechRecognizer(activity);
        val speechRecognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())

        speechRecognizer.setRecognitionListener(object : RecognitionListener {
            override fun onReadyForSpeech(bundle: Bundle) {}
            override fun onBeginningOfSpeech() {
                binding.editText.setText("")
                binding.editText.hint = "Listening..."
            }

            override fun onRmsChanged(v: Float) {}
            override fun onBufferReceived(bytes: ByteArray) {}
            override fun onEndOfSpeech() {}
            override fun onError(i: Int) {}
            override fun onResults(bundle: Bundle) {
                val data = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                binding.editText.setText(data!![0])
            }

            override fun onPartialResults(bundle: Bundle) {}
            override fun onEvent(i: Int, bundle: Bundle) {}
        })

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
        _binding = null
        speechRecognizer.destroy();

    }

    override fun onDetach() {
        super.onDetach()
        buttonListener = null
    }

    companion object {

    }
}