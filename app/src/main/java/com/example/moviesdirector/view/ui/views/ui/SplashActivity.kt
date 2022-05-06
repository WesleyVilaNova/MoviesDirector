package com.example.moviesdirector.view.ui.views.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.postDelayed
import com.example.moviesdirector.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initSplashScreen()
    }

    private fun initSplashScreen() {
        Handler(Looper.getMainLooper()).postDelayed(5000, 5000) {
            val intent = Intent(this, ListMoviesActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}