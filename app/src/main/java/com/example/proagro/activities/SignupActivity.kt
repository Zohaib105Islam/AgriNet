package com.example.proagro.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.proagro.databinding.ActivitySignupBinding
import com.example.proagro.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class SignupActivity : AppCompatActivity() {

    val auth=FirebaseAuth.getInstance()
    val database = FirebaseDatabase.getInstance().reference

    val binding: ActivitySignupBinding by lazy {
        ActivitySignupBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.signupBtn.setOnClickListener() {
            signUp()
        }

        binding.gotoLogin.setOnClickListener({
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        })

    }

    private fun signUp(){
        val name = binding.signupName.text.toString().trim()
        val email=binding.signupEmail.text.toString().trim()
        val pass=binding.signupPass.text.toString().trim()
        val address=binding.signupAddress.text.toString().trim()
        val phone=binding.signupPhone.text.toString().trim()

        if (email.isEmpty()){
            binding.signupEmail.error="Enter your email"
           // binding.signupPass.error="Enter your password"
        }
        else if (pass.isEmpty()){
            binding.signupPass.error="Enter your password"
        }else{

            auth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign up successful, navigate to main screen
                        Toast.makeText(this,"Sign up success!", Toast.LENGTH_SHORT).show()
                        saveUserData()
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    } else {
                        // Handle errors
                        Toast.makeText(this, "Sign up failed! ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }


        }

    }

    private fun saveUserData() {
        val name = binding.signupName.text.toString().trim()
        val email=binding.signupEmail.text.toString().trim()
        val pass=binding.signupPass.text.toString().trim()
        val address=binding.signupAddress.text.toString().trim()
        val phone=binding.signupPhone.text.toString().trim()

        val user = UserModel(name,email,pass,phone,address)
       val userEmail = auth.currentUser?.email.toString() ?: ""
        val hashedEmail = userEmail.replace('.', ',')

        database.child("user").child(hashedEmail).setValue(user)
    }


}