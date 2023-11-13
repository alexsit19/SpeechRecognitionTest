package com.example.myapplication

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.fragments.DeepSpeechFragment
import com.example.myapplication.fragments.GoogleApiFragment
import com.example.myapplication.fragments.MainFragment
import com.example.myapplication.fragments.SpeechRecognitionFragment
import android.Manifest


class MainActivity : AppCompatActivity(), ButtonListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, MainFragment())
            .commit()

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
            checkPermission();
        }
    }

    override fun navigateToGoogleApiFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, GoogleApiFragment())
            .commit()
    }

    override fun navigateToDeepSpeechFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, DeepSpeechFragment())
            .commit()
    }

    override fun navigateToSpeechRecognitionFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, SpeechRecognitionFragment())
            .commit()
    }

    override fun navigateToMainFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, MainFragment())
            .commit()
    }

    private fun checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf<String>(Manifest.permission.RECORD_AUDIO),
                RecordAudioRequestCode
            )
        }
    }

    companion object {
        const val RecordAudioRequestCode = 1
    }
}