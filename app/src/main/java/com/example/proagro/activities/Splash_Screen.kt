package com.example.proagro.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.proagro.R
import com.google.firebase.auth.FirebaseAuth

class Splash_Screen : AppCompatActivity() {

    val auth = FirebaseAuth.getInstance()
    val currentUser= auth.currentUser
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed(Runnable {

            checkUserExist()

            finish()

        },1000)

    }

    private fun checkUserExist() {
        if (currentUser != null)
        {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }else{
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }


}