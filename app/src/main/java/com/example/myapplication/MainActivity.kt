package com.example.myapplication

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var buttonListener: ButtonListener

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        buttonListener = ButtonListener.Base(supportFragmentManager)
        buttonListener.navigateToMainFragment()

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
            checkPermission()
        }
    }

    private fun checkPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf<String>(Manifest.permission.RECORD_AUDIO),
            RecordAudioRequestCode
        )
    }

    companion object {
        const val RecordAudioRequestCode = 1
    }
}