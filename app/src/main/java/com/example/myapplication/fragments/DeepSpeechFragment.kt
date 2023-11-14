package com.example.myapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.BaseFragment
import com.example.myapplication.databinding.FragmentDeepSpeechBinding

class DeepSpeechFragment : BaseFragment<FragmentDeepSpeechBinding>() {

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentDeepSpeechBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setNavigationOnClickListener {
            buttonListener.navigateToMainFragment()
        }
    }

    companion object {}
}