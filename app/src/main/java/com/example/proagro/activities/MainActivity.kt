package com.example.proagro.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.proagro.R
import com.example.proagro.databinding.ActivityMainBinding
import com.example.proagro.fragments.BottomSheetFragment
import com.example.proagro.fragments.NotificationBottomSheet
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {


  private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val navController = findNavController(R.id.fragmentContainerView)
        binding.bottomNavigationView.setupWithNavController(navController)


    }
}