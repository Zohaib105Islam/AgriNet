package com.example.proagro.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proagro.adapters.RecentOrderAdapter
import com.example.proagro.databinding.ActivityRecentOrderProductsBinding
import com.example.proagro.model.OrderDetails
class RecentOrderProducts : AppCompatActivity() {

    private val binding : ActivityRecentOrderProductsBinding by lazy {
        ActivityRecentOrderProductsBinding.inflate(layoutInflater)
    }

    private lateinit var allProductNames : ArrayList<String>
    private lateinit var allProductImages : ArrayList<String>
    private lateinit var allProductPrices : ArrayList<String>
    private lateinit var allProductQuantities : ArrayList<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.backBtn.setOnClickListener(){
            finish()
        }

//        val recentOrderItems: ArrayList<OrderDetails>? =
//            intent.getSerializableExtra("RecentBuyOrderItemList") as? ArrayList<OrderDetails>
//
//        if (recentOrderItems != null && recentOrderItems.isNotEmpty()) {
//            recentOrderItems?.let { orderDetails ->
//            if (orderDetails.isNotEmpty()){
//            val recentOrderItem: OrderDetails = orderDetails[0]
//
//            allProductNames = recentOrderItem.productNames as ArrayList<String>
//            allProductImages = recentOrderItem.productImages as ArrayList<String>
//            allProductPrices = recentOrderItem.productPrices as ArrayList<String>
//            allProductQuantities = recentOrderItem.productQuantities as ArrayList<Int>
//
//        }}} else {
//            // Handle the case where recentOrderItems is null or empty
//            // Display an error message or take appropriate action
//            Toast.makeText(this, "List empty", Toast.LENGTH_LONG).show()
//            // ...
//            return // Don't proceed to setAdapter() if there is no valid data
//        }
//
//        setAdapter()
//    }


// Retrieve recent order items from intent
        val recentOrderItems: ArrayList<OrderDetails>? =
            intent.getSerializableExtra("RecentBuyOrderItemList") as? ArrayList<OrderDetails>

        if (recentOrderItems != null && recentOrderItems.isNotEmpty()) {
            val recentOrderItem: OrderDetails = recentOrderItems[0]

            allProductNames = recentOrderItem.productNames as ArrayList<String>
            allProductImages = recentOrderItem.productImages as ArrayList<String>
            allProductPrices = recentOrderItem.productPrices as ArrayList<String>
            allProductQuantities = recentOrderItem.productQuantities as ArrayList<Int>
        } else {
            // Handle the case where recentOrderItems is null or empty
            // Display an error message or take appropriate action
            Toast.makeText(this, "List empty", Toast.LENGTH_LONG).show()
            // ...
            return // Don't proceed to setAdapter() if there is no valid data
        }

        setAdapter()}

    private fun setAdapter() {
        val rv: RecyclerView = binding.recentRecyclerView
        rv.layoutManager = LinearLayoutManager(this)
        val adapter: RecentOrderAdapter = RecentOrderAdapter(this,
            allProductNames,
            allProductImages,
            allProductPrices,
            allProductQuantities
        )
        rv.adapter = adapter
    }
}

//========================================================================================================
//class RecentOrderProducts : AppCompatActivity() {
//
//    private val binding : ActivityRecentOrderProductsBinding by lazy {
//        ActivityRecentOrderProductsBinding.inflate(layoutInflater)
//    }
//
//    private lateinit var allProductNames : ArrayList<String>
//    private lateinit var allProductImages : ArrayList<String>
//    private lateinit var allProductPrices : ArrayList<String>
//    private lateinit var allProductQuantities : ArrayList<Int>
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(binding.root)
//
//
//        //=============================================
//        val recentOrderItems: ArrayList<OrderDetails>? =
//            intent.getSerializableExtra("RecentBuyOrderItemList") as? ArrayList<OrderDetails>
//
//        if (recentOrderItems != null && recentOrderItems.isNotEmpty()) {
//            // Process the recentOrderItems here
//            recentOrderItems?.let { orderDetails ->
//            if (orderDetails.isEmpty()){
//                val recentOrderItem: OrderDetails = orderDetails[0]
//
//
//            allProductNames = recentOrderItem.productNames as ArrayList<String>
//            allProductImages = recentOrderItem.productImages as ArrayList<String>
//            allProductPrices = recentOrderItem.productPrices as ArrayList<String>
//            allProductQuantities = recentOrderItem.productQuantities as ArrayList<Int>
//            // ...
//        }}} else {
//            // Handle the case where recentOrderItems is null or empty
//            // Display an error message or take appropriate action
//            Toast.makeText(this,"Liast empty",Toast.LENGTH_LONG).show()
//
//            // ...
//        }
//        setAdapter()
//    }
//    //==============================
//
//    private fun setAdapter() {
//        val rv: RecyclerView = binding.recentRecyclerView
//        rv.layoutManager=LinearLayoutManager(this)
//        val adapter: RecentOrderAdapter = RecentOrderAdapter(this,
//            allProductNames,
//            allProductImages,
//            allProductPrices,
//            allProductQuantities
//        )
//        rv.adapter=adapter
//    }
//}
//===============================================================================================================

//        val recentOrderItems : ArrayList<OrderDetails>? = intent.getSerializableExtra("RecentBuyOrderItem") as ArrayList<OrderDetails>
//
//        recentOrderItems?.let { orderDetails ->
//            if (orderDetails.isEmpty()){
//                val recentOrderItem: OrderDetails = recentOrderItems[0]
//
//                allProductNames=recentOrderItem.productNames as ArrayList<String>
//                allProductImages=recentOrderItem.productImages as ArrayList<String>
//                allProductPrices=recentOrderItem.productPrices as ArrayList<String>
//                allProductQuantities=recentOrderItem.productQuantities as ArrayList<Int>
//
//            }
//        }
//
//        setAdapter()
//    }
//