package com.example.proagro.activities

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.proagro.databinding.ActivityChooseLocationBinding

class ChooseLocationActivity : AppCompatActivity() {

    val bindings: ActivityChooseLocationBinding by lazy {
        ActivityChooseLocationBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bindings.root)

        val cityList= arrayOf("Islambad","Lahore","Multan","Karachi","Peshawar","Quetta")
        val ArrayAdapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,cityList)
        val autoCompleteTextView=bindings.listLocation
        autoCompleteTextView.setAdapter(ArrayAdapter)
    }
}