package com.codingtest.travelapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codingtest.travelapp.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityWelcomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCreateNewAccount.setOnClickListener {
            startActivity(RegisterActivity.getIntent(this))
        }
    }
}