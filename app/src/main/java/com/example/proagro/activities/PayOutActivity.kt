package com.example.proagro.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.proagro.databinding.ActivityPayOutBinding
import com.example.proagro.fragments.CongratsBottomSheet
import com.example.proagro.fragments.Fragment_cart
import com.example.proagro.model.OrderDetails
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class PayOutActivity : AppCompatActivity() {

    private val binding: ActivityPayOutBinding by lazy {
        ActivityPayOutBinding.inflate(layoutInflater)
    }

    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference

    private lateinit var name: String
    private lateinit var address: String
    private lateinit var phone: String
    private lateinit var totalAmount: String

    private lateinit var productItemName: ArrayList<String>
    private lateinit var productItemPrice: ArrayList<String>
    private lateinit var productItemDescription: ArrayList<String>
    private lateinit var productItemImage: ArrayList<String>
    private lateinit var productItemIngredient: ArrayList<String>
    private lateinit var productItemQuantity: ArrayList<Int>

    private lateinit var userId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //intialize firebase
        auth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance().getReference()



        // set user data
        setUserData()


//==============================================================================================================
        // get user details from firebase
        val intent: Intent = intent
        productItemName = intent.getStringArrayListExtra("ProductItemName") as ArrayList<String>
        // productItemPrice = intent.getStringArrayListExtra("ProductItemPrice") as ArrayList<String>
        productItemDescription =
            intent.getStringArrayListExtra("ProductItemDescription") as ArrayList<String>
        productItemImage = intent.getStringArrayListExtra("ProductItemImage") as ArrayList<String>
        productItemIngredient =
            intent.getStringArrayListExtra("ProductItemIngredient") as ArrayList<String>
        productItemQuantity =
            intent.getIntegerArrayListExtra("ProductItemQuantity") as ArrayList<Int>

        productItemPrice = intent.getStringArrayListExtra("ProductItemPrice") as ArrayList<String>

// Check if productItemPrice is not empty
        if (productItemPrice.isNotEmpty()) {
            totalAmount = "$" + calculateTotalAmount(productItemPrice).toString()
            // Disable the EditText
            binding.totalAmount.isEnabled = false
            // Set totalAmount as text in the EditText
            binding.totalAmount.setText(totalAmount)
        } else {
            // Handle the case where productItemPrice is empty
            Log.w("CalculateTotalAmount", "ProductItemPrice is empty.")
        }
//=====================================================================================

//============================================================
        binding.backBtn.setOnClickListener {
            finish()
        }

        binding.placeOrdeBtn.setOnClickListener {

            // get data from textviews
            name=binding.profileName.text.toString().trim()
            address=binding.profileAddress.text.toString().trim()
            phone=binding.profilePhone.text.toString().trim()

            if (name.isBlank() && address.isBlank() && phone.isBlank()){
                Toast.makeText(this,"Please enter all the details !!",Toast.LENGTH_LONG).show()
            }else{
                placeOrder()
            }
        }
    }

    private fun placeOrder() {

        // get current user email ================
        val userEmail = auth.currentUser?.email.toString() ?: ""
        val hashedEmail = userEmail.replace('.', ',')
//=========================================
        val time:Long = System.currentTimeMillis()
        val itemPushKey:String? = databaseReference.child("PendingOrderDetails").push().key

        val orderDetails =OrderDetails(hashedEmail,name,productItemName,productItemPrice,productItemImage,productItemQuantity,address,totalAmount,phone,time,itemPushKey,false,false)
        val orderRefrence: DatabaseReference = databaseReference.child("PendingOrderDetails").child(itemPushKey!!)
        orderRefrence.setValue(orderDetails).addOnSuccessListener {
            val bottomSheet = CongratsBottomSheet()
            bottomSheet.show(supportFragmentManager, "Test")
            removeProductFromCart()
            addOrderToHistory(orderDetails)
        } .addOnFailureListener{
            Toast.makeText(this,"Order failed !!",Toast.LENGTH_LONG).show()
        }

    }

    private fun addOrderToHistory(orderDetails: OrderDetails) {
        // get current user email ================
        val userEmail = auth.currentUser?.email.toString() ?: ""
        val hashedEmail = userEmail.replace('.', ',')
//=========================================

        databaseReference.child("user").child(hashedEmail).child("BuyHistory")
            .child(orderDetails.itemPushKey!!)
            .setValue(orderDetails)
            .addOnSuccessListener {  }
    }

    private fun removeProductFromCart() {
        // get current user email ================
        val userEmail = auth.currentUser?.email.toString() ?: ""
        val hashedEmail = userEmail.replace('.', ',')
//=========================================
        val cartItemsRefrence: DatabaseReference=databaseReference.child("user").child(hashedEmail).child("CartItems")
        cartItemsRefrence.removeValue()
    }

    //=====================================================================================
    private fun setUserData() {
        val user: FirebaseUser? = auth.currentUser
        if (user != null) {
            val userEmail: String = user.email?.toString() ?: ""
            val hashedEmail = userEmail.replace('.', ',')

            val userReference: DatabaseReference =
                databaseReference.child("user").child(hashedEmail)

            userReference.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {

                    if (snapshot.exists()) {
                        val names: String =
                            snapshot.child("name").getValue(String::class.java) ?: ""
                        val addresses: String =
                            snapshot.child("address").getValue(String::class.java) ?: ""
                        val phones: String =
                            snapshot.child("phone").getValue(String::class.java) ?: ""

                        binding.apply {
                            profileName.setText(names)
                            profileAddress.setText(addresses)
                            profilePhone.setText(phones)
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {

                }
            })
        }
    }

    //============================================
    private fun calculateTotalAmount(productItemPrice: ArrayList<String>): Int {
        var totalAmount = 0
        Log.d("CalculateTotalAmount", "ProductItemPrice: ${this.productItemPrice}")

        for (i: Int in 0 until this.productItemPrice.size) {
            try {
                val price: String = this.productItemPrice[i]

                // Check if the price is not empty and does not contain unexpected characters
                //    && price.matches(Regex("-?\\d+"))
                if (price.isNotEmpty() && price.matches(Regex("-?\\d+"))) {
                    val lastChar: Char = price.last()
                    val priceIntValue: Int = if (lastChar == '$') {
                        price.dropLast(1).toInt()
                    } else {
                        price.toInt()
                    }
                    var quantity = productItemQuantity[i]
                    totalAmount += priceIntValue * quantity
                } else {
                    // Log a warning or handle the case where the price is empty or contains unexpected characters
                    Log.w("CalculateTotalAmount", "Unexpected price format at index: $i")
                }
            } catch (e: Exception) {
                // Handle the exception, log the error, or provide a default value
                Log.e("CalculateTotalAmount", "Error calculating total amount: ${e.message}")
            }
        }

        return totalAmount
    }
}