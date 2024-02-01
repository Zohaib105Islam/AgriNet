package com.example.proagro.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.proagro.R
import com.example.proagro.databinding.ActivitySplashScreenBinding
import com.google.firebase.auth.FirebaseAuth

class Splash_Screen : AppCompatActivity() {

    val binding : ActivitySplashScreenBinding by lazy {
        ActivitySplashScreenBinding.inflate(layoutInflater)
    }

    val auth = FirebaseAuth.getInstance()
    val currentUser= auth.currentUser
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        // Load the animation from the XML file
        val fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.alphain)

        // Set an optional AnimationListener
        fadeInAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
                // Animation started
            }

            override fun onAnimationEnd(animation: Animation) {
                // Animation ended
            }

            override fun onAnimationRepeat(animation: Animation) {
                // Animation repeated (if set to repeat)
            }
        })

        // Start the animation on your ImageView
        binding.splashImage.startAnimation(fadeInAnimation)


        Handler().postDelayed(Runnable {

            checkUserExist()

            finish()

        },2000)

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