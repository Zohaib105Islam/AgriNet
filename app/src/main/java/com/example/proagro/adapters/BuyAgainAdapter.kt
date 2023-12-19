package com.example.proagro.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proagro.databinding.BuyAgainItemHistoryBinding

class BuyAgainAdapter (private val buyAgainProductNames: ArrayList<String>,
                       private val buyAgainProductPrice: ArrayList<String>,
                       private val buyAgainProductImage: ArrayList<Int>)
    : RecyclerView.Adapter<BuyAgainAdapter.BuyAgainViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuyAgainViewHolder {
        val binding=BuyAgainItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BuyAgainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BuyAgainViewHolder, position: Int) {
        holder.bind(buyAgainProductNames[position],buyAgainProductPrice[position],buyAgainProductImage[position])
    }
    override fun getItemCount(): Int =buyAgainProductNames.size

    class BuyAgainViewHolder(private val binding: BuyAgainItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root){
        fun bind(productName: String, productPrice: String, productImage: Int) {

            binding.BuyAgainProductName.text=productName
            binding.BuyAgainProductPrice.text=productPrice
            binding.BuyAgainProductImg.setImageResource(productImage)

        }

    }
}