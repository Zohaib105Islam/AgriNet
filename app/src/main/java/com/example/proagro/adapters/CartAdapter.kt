package com.example.proagro.adapters

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proagro.databinding.CartItemsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class CartAdapter(
    private val context: Context,
    private val cartItems: MutableList<String>,
    private val cartItemPrices: MutableList<String>,
    private val cartImages: MutableList<String>,
    private var cartDescription: MutableList<String>,
    private var cartQuantity: MutableList<Int>,
    private var cartIngredient: MutableList<String>
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    //instance Firebase
    private val auth = FirebaseAuth.getInstance()

    init {
        // Initialize Firebase
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val userEmail: String = auth.currentUser?.email ?: ""
        val hashedEmail = userEmail.replace('.', ',')
        val cartItemNumber: Int = cartItems.size

        itemQuantities = IntArray(cartItemNumber) { 1 }
        cartItemsRefrence = database.reference.child("user").child(hashedEmail).child("CartItems")
    }

    companion object {



        private var itemQuantities: IntArray = intArrayOf()
        private lateinit var cartItemsRefrence: DatabaseReference

    }

    // private var itemQuantities: MutableList<Int> = Array(cartItems.size) { 1 }.toMutableList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        return CartViewHolder(
            CartItemsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return cartItems.size
    }

    fun getUpdatedProductQuantities(): MutableList<Int> {
        val productQuantity : MutableList<Int> = mutableListOf<Int>()
        productQuantity.addAll(cartQuantity)
        return productQuantity

    }


    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = cartItems[position]
        val itemPrice = cartItemPrices[position]
        val image = cartImages[position]
        val quantity = itemQuantities[position]

        holder.bind(item, itemPrice, image, quantity)
    }

    inner class CartViewHolder(private val binding: CartItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: String, itemPrice: String, image: String, quantity: Int) {
            binding.cartItemName.text = item
            binding.cartItemPrice.text = itemPrice

            //Load Image
            val uri = Uri.parse(image)
            Glide.with(context).load(uri).into(binding.cartImg)

            binding.cartItemQuantityTv.text = quantity.toString()

            // Increase quantity Listner
            binding.cartImgbtnAdd.setOnClickListener {
                increaseQuantity(position)
            }

            // Decrease quantity Listner
            binding.cartImgbtnMinus.setOnClickListener {
                decreaseQuantity(position)
            }

            binding.cartImgbtnDelet.setOnClickListener {
                val itemPosition: Int = adapterPosition
                if (itemPosition != RecyclerView.NO_POSITION) {
                    delete(itemPosition)
                }
            }
        }

        private fun increaseQuantity(position: Int) {
            if (position < itemQuantities.size && itemQuantities[position] < 10) {
                itemQuantities[position]++
                cartQuantity[position]= itemQuantities[position]
                binding.cartItemQuantityTv.text = itemQuantities[position].toString()
            }
        }

        private fun decreaseQuantity(position: Int) {
            if (position < itemQuantities.size && itemQuantities[position] > 1) {
                itemQuantities[position]--
                cartQuantity[position]= itemQuantities[position]
                binding.cartItemQuantityTv.text = itemQuantities[position].toString()
            }
        }


        fun delete(position: Int) {
            getUniqueKeyAtPosition(position) { uniqueKey ->
                if (uniqueKey != null) {
                    removeItem(position, uniqueKey)
                }
            }
        }

        fun removeItem(position: Int, uniqueKey: String) {
            getUniqueKeyAtPosition(position) { uniqueKey ->
                if (uniqueKey != null) {
                    cartItemsRefrence.child(uniqueKey).removeValue()
                        .addOnSuccessListener {
                            // Remove the item from local lists
                            cartItems.removeAt(position)
                            cartItemPrices.removeAt(position)
                            cartImages.removeAt(position)
                            cartDescription.removeAt(position)
                            cartIngredient.removeAt(position)
                            cartQuantity.removeAt(position)

                            // Notify adapter about item removal
                            notifyItemRemoved(position)
                            notifyItemRangeChanged(position, itemCount)

                            // Show a success message
                            Toast.makeText(context, "Product Deleted !!", Toast.LENGTH_LONG).show()
                        }
                        .addOnFailureListener {
                            // Show a failure message
                            Toast.makeText(context, "Failed to Delete!!", Toast.LENGTH_LONG).show()
                        }
                } else {
                    // Handle the case when uniqueKey is null or empty
                    // Show an error message
                    Toast.makeText(context, "Unable to find item key", Toast.LENGTH_LONG).show()
                }
            }
        }

        private fun getUniqueKeyAtPosition(positionRetrieve: Int, onComplete: (String?) -> Unit) {
            cartItemsRefrence.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    var uniqueKey: String? = null

                    // Loop through snapshot children
                    snapshot.children.forEachIndexed { index, dataSnapshot ->
                        if (index == positionRetrieve) {
                            // Get the key of the current dataSnapshot
                            uniqueKey = dataSnapshot.key
                            return@forEachIndexed
                        }
                    }

                    // Call onComplete with the obtained uniqueKey
                    onComplete(uniqueKey)
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle onCancelled if needed
                    Toast.makeText(context, "Snapshot Error", Toast.LENGTH_LONG).show()
                }
            })
        }
    }
}
