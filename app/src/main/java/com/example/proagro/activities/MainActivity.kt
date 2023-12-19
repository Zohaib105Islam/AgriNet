package com.example.proagro.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.proagro.R
import com.example.proagro.databinding.ActivityMainBinding
import com.example.proagro.fragments.BottomSheetFragment
import com.example.proagro.fragments.NotificationBottomSheet

class MainActivity : AppCompatActivity() {

  private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val navController = findNavController(R.id.fragmentContainerView)
        binding.bottomNavigationView.setupWithNavController(navController)

// Open Bottom Sheet for Notification
        binding.notificationBtn.setOnClickListener {
            val bottomSheetDialog = NotificationBottomSheet()
            bottomSheetDialog.show(supportFragmentManager, "Test")
        }

    }
}