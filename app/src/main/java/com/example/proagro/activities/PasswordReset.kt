package com.example.proagro.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.proagro.R
import com.example.proagro.databinding.ActivityPasswordResetBinding
import com.google.firebase.auth.FirebaseAuth

class PasswordReset : AppCompatActivity() {

    val binding : ActivityPasswordResetBinding by  lazy {
        ActivityPasswordResetBinding.inflate(layoutInflater)
    }

    val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.backBtn.setOnClickListener(){
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        binding.resetPassBtn.setOnClickListener(){
            resetPassword()
        }

    }

    private fun resetPassword() {
            val email=binding.resetPassEmail.text.toString().trim()

            // Validate input
            if (TextUtils.isEmpty(email)) {
                Toast.makeText(this, "Please enter your email address!", Toast.LENGTH_LONG).show()
                return
            }

            // Send password reset email
            auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Password reset email sent! Varify and then Login", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this, "Error sending reset email: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                    }
                }

        }
    }
