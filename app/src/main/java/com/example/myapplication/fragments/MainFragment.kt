package com.example.myapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.BaseFragment
import com.example.myapplication.databinding.FragmentMainBinding

class MainFragment : BaseFragment<FragmentMainBinding>() {

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentMainBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setButtonListeners()
    }

    private fun setButtonListeners() {
        binding.deepSpeechBtn.setOnClickListener {
            buttonListener.navigateToDeepSpeechFragment()
        }

        binding.googleApiBtn.setOnClickListener {
            buttonListener.navigateToGoogleApiFragment()
        }

        binding.speechRecognitionBtn.setOnClickListener {
            buttonListener.navigateToSpeechRecognitionFragment()
        }
    }

    companion object {}

}