package com.example.proagro.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proagro.databinding.CartItemsBinding



class CartAdapter(
    private val cartItems: MutableList<String>,
    private val cartItemPrices: MutableList<String>,
    private val cartImages: MutableList<Int>
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    private var itemQuantities: MutableList<Int> = Array(cartItems.size) { 1 }.toMutableList()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        return CartViewHolder(CartItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return cartItems.size
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

        fun bind(item: String, itemPrice: String, image: Int, quantity: Int) {
            binding.cartItemName.text = item
            binding.cartItemPrice.text = itemPrice
            binding.cartImg.setImageResource(image)
            binding.cartItemQuantityTv.text = quantity.toString()

            // Increase quantity Listner
            binding.cartImgbtnAdd.setOnClickListener {
                // Increase quantity
                increaseQuantity(position)
            }

            // Decrease quantity Listner
            binding.cartImgbtnMinus.setOnClickListener {
                // Decrease quantity
                decreaseQuantity(position)
            }

            // Delete item Listner
            binding.cartImgbtnDelet.setOnClickListener {
                delete(position)
            }

        }

        //Method for Increase quantity
        private fun increaseQuantity(position: Int) {
            if (itemQuantities[position] < 10) {
                itemQuantities[position]++
                binding.cartItemQuantityTv.text = itemQuantities[position].toString()
            }
        }

        //Method for decrease quantity
        private fun decreaseQuantity(position: Int) {
            if (itemQuantities[position] > 1) {
                itemQuantities[position]--
                binding.cartItemQuantityTv.text = itemQuantities[position].toString()
            }
        }

        //Method for delete quantity
        fun delete(position: Int) {
            cartItems.removeAt(position)
            cartItemPrices.removeAt(position)
            cartImages.removeAt(position)
            itemQuantities.removeAt(position)
            notifyDataSetChanged()
        }

    }
}

