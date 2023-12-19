package com.example.proagro.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proagro.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {

    private val binding: ActivityDetailsBinding by lazy{
        ActivityDetailsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.finishBtn.setOnClickListener{
            finish()
        }

        val foodName=intent.getStringExtra("ProductItemName")
        val foodImage=intent.getIntExtra("ProductItemImage",0)
        binding.detailProductName.text=foodName
        binding.detailProductImage.setImageResource(foodImage)
    }
}