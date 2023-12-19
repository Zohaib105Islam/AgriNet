package com.example.proagro.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proagro.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {

    val binding: ActivitySignupBinding by lazy {
        ActivitySignupBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.gotoLogin.setOnClickListener({
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        })

    }
}