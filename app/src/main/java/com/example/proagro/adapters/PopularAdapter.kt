package com.example.proagro.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proagro.activities.DetailsActivity
import com.example.proagro.databinding.PopularItemsBinding

class PopularAdapter (
    private val items:List<String>,
    private val price:List<String>,
    private val image:List<Int>,
    private val requireContext:Context
): RecyclerView.Adapter<PopularAdapter.PopularViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
       return PopularViewHolder(PopularItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
       val item=items[position]
        val images=image[position]
        val pric=price[position]
        holder.bind(item,images,pric)
        holder.itemView.setOnClickListener{
            val intent= Intent(requireContext, DetailsActivity::class.java)
            intent.putExtra("ProductItemName",item)
            intent.putExtra("ProductItemImage",images)
            requireContext.startActivity(intent)
        }
    }


    class PopularViewHolder(private val binding: PopularItemsBinding) : RecyclerView.ViewHolder(binding.root){
        private val imageview=binding.imagePopular1
        fun bind(item: String, images: Int, pric: String) {
            binding.productNameTv1.text=item
            binding.pricePopular1.text=pric
            imageview.setImageResource(images)


        }
    }

  }
////=======MVVM codde of above adapter class============================================
//
////Create a model class:
//// Define a PopularItem data class that represents
////an item displayed in the adapter. This class should contain
////properties for item name, price, and image resource ID.
//
//data class PopularItem(
//    val name: String,
//    val price: String,
//    val image: Int
//)
//
////Create a view model:
//// Create a PopularViewModel class
//// that exposes a list of PopularItem objects and handles navigation events.
//class PopularViewModel : ViewModel() {
//
//    private val _popularItems = MutableLiveData<List<PopularItem>>()
//    val popularItems: LiveData<List<PopularItem>> = _popularItems
//
//    fun onItemClicked(item: PopularItem) {
//        // Handle item click event and navigate to detail activity
//        val intent = Intent(context, DetailsActivity::class.java)
//        intent.putExtra("ProductItemName", item.name)
//        intent.putExtra("ProductItemImage", item.image)
//        context.startActivity(intent)
//    }
//
//    // Define functions to update popularItems list
//    // ...
//}
//
////Update adapter:
//// Modify the PopularAdapter class to receive a list
//// of PopularItem objects instead of separate lists
//// for item names, prices, and images. Bind data using
//// data binding and handle click events with the help of the viewmodel
//class PopularAdapter(
//    private val viewModel: PopularViewModel,
//    private val requireContext: Context
//) : RecyclerView.Adapter<PopularAdapter.PopularViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
//        return PopularViewHolder(
//            PopularItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        )
//    }
//
//    override fun getItemCount(): Int = viewModel.popularItems.value?.size ?: 0
//
//    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
//        val item = viewModel.popularItems.value?.get(position)
//        holder.binding.apply {
//            productNameTv1.text = item?.name
//            pricePopular1.text = item?.price
//            imageview.setImageResource(item?.image ?: 0)
//            root.setOnClickListener {
//                viewModel.onItemClicked(item)
//            }
//        }
//    }
//
//    class PopularViewHolder(val binding: PopularItemsBinding) : RecyclerView.ViewHolder(binding.root)
//}
//
