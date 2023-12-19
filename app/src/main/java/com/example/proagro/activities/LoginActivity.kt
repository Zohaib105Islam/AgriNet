package com.example.proagro.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proagro.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    val bindings: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bindings.root)

        bindings.gotosignup.setOnClickListener({
            startActivity(Intent(this, SignupActivity::class.java))
            finish()
        })
    }
}