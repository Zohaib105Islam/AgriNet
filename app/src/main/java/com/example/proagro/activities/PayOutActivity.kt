package com.example.proagro.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.proagro.R
import com.example.proagro.databinding.ActivityMainBinding
import com.example.proagro.databinding.ActivityPayOutBinding
import com.example.proagro.fragments.CongratsBottomSheet
import com.example.proagro.fragments.Fragment_cart

class PayOutActivity : AppCompatActivity() {

    private val binding: ActivityPayOutBinding by lazy {
        ActivityPayOutBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.backBtn.setOnClickListener{
            var intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.placeOrdeBtn.setOnClickListener{
            val bottomSheet=CongratsBottomSheet()
            bottomSheet.show(supportFragmentManager,"Test")

        }
    }
}