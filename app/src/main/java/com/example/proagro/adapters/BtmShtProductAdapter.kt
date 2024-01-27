package com.example.proagro.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proagro.activities.DetailsActivity
import com.example.proagro.databinding.ProductBottomSheetBinding
import com.example.proagro.model.ProductItem

class BtmShtProductAdapter(
    private val requireContext: Context,
    private val productItems: List<ProductItem>

) : RecyclerView.Adapter<BtmShtProductAdapter.BtmShtViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BtmShtViewHolder {
        return BtmShtViewHolder(ProductBottomSheetBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: BtmShtViewHolder, position: Int) {

        holder.bind(position)
   }

    override fun getItemCount(): Int = productItems.size

   inner class BtmShtViewHolder(private val binding: ProductBottomSheetBinding) :
        RecyclerView.ViewHolder(binding.root) {

       init {
           binding.root.setOnClickListener{
               val position=adapterPosition
               if (position != RecyclerView.NO_POSITION){
                   openDetailsActivity(position)
               }

           }
       }

       private fun openDetailsActivity(position: Int) {
           val productItem:ProductItem = productItems[position]

           val intent=Intent(requireContext,DetailsActivity::class.java).apply {
               putExtra("productName",productItem.productName)
               putExtra("productPrice",productItem.productPrice)
               putExtra("productDescription",productItem.productDescription)
               putExtra("productImage",productItem.productImage)
               putExtra("productIngredients",productItem.productIngredient)
           }
           requireContext.startActivity(intent)
       }

       //set data into RecyclerView => name, price , image
        fun bind(position: Int) {
           val productItem: ProductItem=productItems[position]

            binding.apply {
                productNameTv2.text=productItem.productName
                pricePopular2.text=productItem.productPrice
                val uri:Uri= Uri.parse(productItem.productImage)
                Glide.with(requireContext).load(uri).into(imagePopular2)


            }

        }

    }

}
