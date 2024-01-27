package com.example.proagro.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proagro.activities.PayOutActivity
import com.example.proagro.adapters.CartAdapter
import com.example.proagro.databinding.FragmentCartBinding
import com.example.proagro.model.CartItems
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class Fragment_cart : Fragment() {

    private lateinit var binding: FragmentCartBinding

    private lateinit var productNames: MutableList<String>
    private lateinit var productPrices: MutableList<String>
    private lateinit var productDescriptions: MutableList<String>
    private lateinit var productImagesUri: MutableList<String>
    private lateinit var productIngredients: MutableList<String>
    private lateinit var quantity: MutableList<Int>
    private lateinit var adapter: CartAdapter

    private lateinit var auth: FirebaseAuth
    private lateinit var userEmail: String
    private lateinit var database: FirebaseDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCartBinding.inflate(inflater, container, false)


        auth = FirebaseAuth.getInstance()

        retrieveCartItems()

        binding.proceedBtn.setOnClickListener {
            getProductsOrderDetail()

        }


        return binding.root
    }

    private fun getProductsOrderDetail() {
        try {
            userEmail = auth.currentUser?.email.toString() ?: ""
            val hashedEmail = userEmail.replace('.', ',')

            val orderIdReference: DatabaseReference =
                database.reference.child("user").child(hashedEmail)
                    .child("CartItems")

            val productName: MutableList<String> = mutableListOf<String>()
            val productPrice: MutableList<String> = mutableListOf<String>()
            val productImage: MutableList<String> = mutableListOf<String>()
            val productDescription: MutableList<String> = mutableListOf<String>()
            val productIngredient: MutableList<String> = mutableListOf<String>()

            val productQuantities = adapter.getUpdatedProductQuantities()

            orderIdReference.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {

                    for (productSnapshot: DataSnapshot in snapshot.children) {
                        // get cartItems to respective list
                        val orderProducts: CartItems? =
                            productSnapshot.getValue(CartItems::class.java)

                        // add product details into list
                        orderProducts?.apply {
                            productName?.let { productName.add(it.toString()) }
                            productPrice?.let { productPrice.add(it.toString()) }
                            productDescription?.let { productDescription.add(it.toString()) }
                            productImage?.let { productImage.add(it.toString()) }
                            productIngredient?.let { productIngredient.add(it.toString()) }
                        }
                    }
                    orderNow()
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(
                        requireContext(),
                        "Order Failed !! please try again !!",
                        Toast.LENGTH_LONG
                    ).show()
                }
            })

        } catch (e: Exception) {
            // Handle any exceptions here
            Toast.makeText(requireContext(), "Please add products in Cart", Toast.LENGTH_LONG)
                .show()
            e.printStackTrace()
        }

    }


    private fun retrieveCartItems() {

        try {

            // database refrence
            database = FirebaseDatabase.getInstance()
            //  userEmail : String= auth.currentUser?.email?: ""
            userEmail = auth.currentUser?.email.toString() ?: ""
            val hashedEmail = userEmail.replace('.', ',')

            val productRefrence: DatabaseReference =
                database.reference.child("user").child(hashedEmail).child("CartItems")

            productNames = mutableListOf()
            productPrices = mutableListOf()
            productDescriptions = mutableListOf()
            productImagesUri = mutableListOf()
            productIngredients = mutableListOf()
            quantity = mutableListOf()

            // fetch data from database
            productRefrence.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (productSnapshot: DataSnapshot in snapshot.children) {
                        // get the cartItems oject from the child node
                        val cartItems: CartItems? = productSnapshot.getValue(CartItems::class.java)

                        cartItems?.productName?.let { productNames.add(it) }
                        // cartItems?.productPrice?.let { productPrices.add(it) }
                        // Check for null and empty product price
                        if (cartItems?.productPrice != null && cartItems.productPrice.isNotEmpty()) {
                            productPrices.add(cartItems.productPrice)

                        }
                        cartItems?.productDescription?.let { productDescriptions.add(it) }
                        cartItems?.productImage?.let { productImagesUri.add(it) }
                        cartItems?.productQuantity?.let { quantity.add(it) }
                        cartItems?.productIngredient?.let { productIngredients.add(it) }
                    }


                    // Inside retrieveCartItems() after onDataChange
                    if (productNames.isNotEmpty() && productPrices.isNotEmpty() && productImagesUri.isNotEmpty() && productDescriptions.isNotEmpty() && quantity.isNotEmpty()) {

                        // Reverse the order of the lists
                        productNames.reverse()
                        productPrices.reverse()
                        productImagesUri.reverse()
                        productDescriptions.reverse()
                        quantity.reverse()

                        setAdapter()
                        adapter.notifyDataSetChanged()
                    } else {
                        // Handle the case where data is not fetched correctly
                        Toast.makeText(context, "No product into Cart", Toast.LENGTH_LONG).show()
                    }

                }

                override fun onCancelled(error: DatabaseError) {
                    // Toast.makeText(context,"Data not fetch !!",Toast.LENGTH_LONG).show()
//==========================================================
                    context?.let {
                        Toast.makeText(it, "Data not fetch !!", Toast.LENGTH_LONG).show()
                    }
//==========================================================
                }
            }
            )

        } catch (e: Exception) {
            Toast.makeText(requireContext(), "Internet problem !! ${e.message}", Toast.LENGTH_LONG)
                .show()
        }
    }

    fun orderNow() {
        if (isAdded && context != null) {
            val intent = Intent(requireContext(), PayOutActivity::class.java)
            intent.apply {
                putExtra("ProductItemName", productNames as ArrayList<String>)
                putExtra("ProductItemPrice", productPrices as ArrayList<String>)
                putExtra("ProductItemDescription", productDescriptions as ArrayList<String>)
                putExtra("ProductItemImage", productImagesUri as ArrayList<String>)
                putExtra("ProductItemIngredient", productIngredients as ArrayList<String>)
                putExtra("ProductItemQuantity", quantity as ArrayList<Int>)
            }
            startActivity(intent)
            // requireActivity().finish()
        }
    }

    private fun setAdapter() {
        adapter = CartAdapter(
            requireContext(),
            this.productNames,
            this.productPrices,
            this.productImagesUri,
            this.productDescriptions,
            this.quantity,
            productIngredients
        )
        binding.cartRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.cartRecyclerView.adapter = adapter
    }

}