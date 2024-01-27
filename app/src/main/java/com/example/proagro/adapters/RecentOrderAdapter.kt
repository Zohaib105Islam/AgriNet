package com.example.proagro.adapters

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proagro.databinding.RecentBuyProductBinding

class RecentOrderAdapter(
    private var context: Context,
    private var productNameList : ArrayList<String> ,
    private var productImageList : ArrayList<String>,
    private var productPriceList : ArrayList<String>,
    private var productQuantityList : ArrayList<Int>
) : RecyclerView.Adapter<RecentOrderAdapter.RecentOrderViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentOrderViewHolder {
        val binding = RecentBuyProductBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RecentOrderViewHolder(binding)
    }

    override fun getItemCount(): Int = productNameList.size

    override fun onBindViewHolder(holder: RecentOrderViewHolder, position: Int) {
        holder.bind(position)
    }

   inner class RecentOrderViewHolder(private val binding: RecentBuyProductBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {

            binding.apply {
                recentBuyName.text=productNameList[position]
                recentBuyPrice.text=productPriceList[position]
                recentBuyQuantity.text= productQuantityList[position].toString()
                val uriString : String =productImageList[position]
                val uri: Uri = Uri.parse(uriString)
                Glide.with(context).load(uri).into(recentBuyImage)

            }
        }

    }
}