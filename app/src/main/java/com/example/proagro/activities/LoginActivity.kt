package com.example.proagro.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.proagro.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    val auth = FirebaseAuth.getInstance()

    val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.loginBtn.setOnClickListener(){
            loginUser()
        }

        binding.resetPass.setOnClickListener(){
            startActivity(Intent(this,PasswordReset::class.java))
            finish()
        }

        binding.gotosignup.setOnClickListener({
            startActivity(Intent(this, SignupActivity::class.java))
            finish()
        })
    }

    private fun loginUser() {

        val email=binding.loginEmail.text.toString().trim()
        val pass=binding.loginPass.text.toString().trim()

        if (email.isEmpty()){
            binding.loginEmail.error="Enter your email"
            binding.loginPass.error="Enter your password"
        }
        else if (pass.isEmpty()){
            binding.loginPass.error="Enter your password"
        }else{

            auth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign up successful, navigate to main screen
                        Toast.makeText(this,"Login success!", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    } else {
                        // Handle errors
                        Toast.makeText(this, "Login failed! ${task.exception?.message}", Toast.LENGTH_LONG).show()
                    }
                }


        }
    }

    private fun resetPassword() {

        val email=binding.loginEmail.text.toString().trim()

        // Validate input
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter your email address!", Toast.LENGTH_LONG).show()
            return
        }

        // Send password reset email
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Password reset email sent!", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Error sending reset email: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                }
            }

    }
}