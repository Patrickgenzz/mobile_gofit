package com.example.mobile_gofit

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mobile_gofit.R
import com.example.mobile_gofit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var pref: SharedPreferences? = null
    private var b:Bundle? = null
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        b = intent.extras
        val id = b?.getString("idBooking")

        id?.let { binding.rvText.text = it }
    }
}