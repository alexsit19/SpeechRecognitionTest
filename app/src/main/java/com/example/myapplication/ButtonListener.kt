package com.example.myapplication

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.myapplication.fragments.DeepSpeechFragment
import com.example.myapplication.fragments.GoogleApiFragment
import com.example.myapplication.fragments.MainFragment
import com.example.myapplication.fragments.SpeechRecognitionFragment

interface ButtonListener {

    fun navigateToGoogleApiFragment()

    fun navigateToDeepSpeechFragment()

    fun navigateToSpeechRecognitionFragment()

    fun navigateToMainFragment()

    class Base(private val supportFragmentManager: FragmentManager) : ButtonListener {
        override fun navigateToGoogleApiFragment() = replace(GoogleApiFragment())
        override fun navigateToDeepSpeechFragment() = replace(DeepSpeechFragment())
        override fun navigateToSpeechRecognitionFragment() = replace(SpeechRecognitionFragment())
        override fun navigateToMainFragment() = replace(MainFragment())
        private fun replace(fragment: Fragment) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
        }
    }
}