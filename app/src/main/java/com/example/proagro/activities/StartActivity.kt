package com.example.proagro.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proagro.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity() {

    val binding: ActivityStartBinding by
    lazy{
        ActivityStartBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.startButton.setOnClickListener({
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        })

    }
}