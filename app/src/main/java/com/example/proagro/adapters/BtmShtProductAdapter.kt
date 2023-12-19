package com.example.proagro.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proagro.activities.DetailsActivity
import com.example.proagro.databinding.ProductBottomSheetBinding

class BtmShtProductAdapter(
    private val productNames: List<String>,
    private val productPrices: List<String>,
    private val productImages: List<Int>,
    private val requireContext:Context
) : RecyclerView.Adapter<BtmShtProductAdapter.BtmShtViewHolder>() {

    private val itemClicklistner: OnClickListener ?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BtmShtViewHolder {
        return BtmShtViewHolder(ProductBottomSheetBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: BtmShtViewHolder, position: Int) {
//        val productName = productNames[position]
//        val productPrice = productPrices[position]
//        val productImage = productImages[position]

//        holder.bind(productName, productImage, productPrice)
        holder.bind(position)
   }

    override fun getItemCount(): Int = productNames.size

   inner class BtmShtViewHolder(private val binding: ProductBottomSheetBinding) :
        RecyclerView.ViewHolder(binding.root) {

       init {
           binding.root.setOnClickListener{
               val position=adapterPosition
               if (position != RecyclerView.NO_POSITION){
                   itemClicklistner?.OnItemClick(position)
               }
               val intent=Intent(requireContext,DetailsActivity::class.java)
               intent.putExtra("ProductItemName",productNames.get(position))
               intent.putExtra("ProductItemImage",productImages.get(position))
               requireContext.startActivity(intent)
           }
       }

        fun bind(position: Int) {
            binding.apply {
                productNameTv2.text=productNames[position]
                pricePopular2.text=productPrices[position]
                imagePopular2.setImageResource(productImages[position])


            }

        }


//        fun bind(productName: String, productImage: Int, productPrice: String) {
//            binding.productNameTv2.text = productName
//            binding.pricePopular2.text = productPrice
//            binding.imagePopular2.setImageResource(productImage)
//
//
//            val intent=Intent(requireContext,DetailsActivity::class.java)
//
//        }


    }
    interface OnClickListener{
        fun OnItemClick(position: Int)

    }
}




//package com.example.proagro.adapters
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.example.proagro.databinding.ProductBottomSheetBinding
//
//class BtmShtProductAdapter(
//    private val productName:List<String>,
//    private val productPrice:List<String>,
//    private val productImage:List<Int>
//
//) : RecyclerView.Adapter<BtmShtProductAdapter.BtmShtViewHolder>()
//
//{
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BtmShtViewHolder {
//        return BtmShtViewHolder(ProductBottomSheetBinding.inflate(LayoutInflater.from(parent.context),parent,false))
//    }
//
//    override fun onBindViewHolder(holder: BtmShtViewHolder, position: Int) {
//        val product=productName[position]
//        val price=productPrice[position]
//        val images=productImage[position]
//        holder.bind(product,images,price)
//    }
//    override fun getItemCount(): Int = productName.size
//
//    class BtmShtViewHolder {
//        fun bind(product: String, images: Int, price: String) {
//
//
//
//        }
//
//    }
//
//}



    //(private val productName:List<String>,private val productPrice:List<String>,private val productImage:List<Int> )
// : RecyclerView.Adapter<BtmShtProductAdapter.BtmShtViewHolder>() {
//
//
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BtmShtViewHolder {
//        return BtmShtViewHolder(ProductBottomSheetBinding.inflate(LayoutInflater.from(parent.context),parent,false))
//    }
//
//
//
//    override fun onBindViewHolder(holder: BtmShtViewHolder, position: Int) {
//        TODO("Not yet implemented")
//    }
//
//    override fun getItemCount(): Int =productName.size
//
//    class BtmShtViewHolder {
//
//    }
//}