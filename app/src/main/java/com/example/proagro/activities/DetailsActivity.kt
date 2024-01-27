package com.example.proagro.activities

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.proagro.databinding.ActivityDetailsBinding
import com.example.proagro.model.CartItems
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class DetailsActivity : AppCompatActivity() {

    private val binding: ActivityDetailsBinding by lazy{
        ActivityDetailsBinding.inflate(layoutInflater)
    }

    private lateinit var auth: FirebaseAuth

    private var productName : String? = null
    private var productPrice : String? = null
    private var productDescription : String? = null
    private var productIngredient : String? = null
    private var productImage : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //Initialize FirebaseAuth
        auth=FirebaseAuth.getInstance()

        binding.addToCartBtn.setOnClickListener(){
            addProductToCart()
        }

        binding.finishBtn.setOnClickListener{
            finish()
        }

         productName=intent.getStringExtra("productName")
        productDescription=intent.getStringExtra("productDescription")
        productIngredient=intent.getStringExtra("productIngredients")
        productImage=intent.getStringExtra("productImage")
        productPrice=intent.getStringExtra("productPrice")

        binding.detailProductName.text=productName
        binding.productDescription.text=productDescription
        binding.productIngredient.text=productIngredient
        Glide.with(this).load(Uri.parse(productImage)).into(binding.detailProductImage)
    }

    private fun addProductToCart() {
        val database =FirebaseDatabase.getInstance().reference
       // val userId:String= auth.currentUser?.uid?: ""
        val userEmail:String= auth.currentUser?.email?: ""
        val hashedEmail = userEmail.replace('.', ',')
        //create CartItems object
        val cartItem=CartItems(productName.toString(),productPrice.toString(),productDescription.toString(),productImage.toString(),1,productIngredient.toString())

        //Save data from cartItems to RealTime Database
         database.child("user").child(hashedEmail).child("CartItems").push().setValue(cartItem)
       // database.child("user").child(userId).child("CartItems").push().setValue(cartItem)
            .addOnSuccessListener {
                Toast.makeText(this,"Product added into Cart successfully!!",Toast.LENGTH_LONG).show()
            }.addOnFailureListener(){
                Toast.makeText(this,"Product not added into Cart!!",Toast.LENGTH_LONG).show()
            }


    }
}