package com.example.proagro.adapters

import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proagro.databinding.BuyAgainItemHistoryBinding
import com.example.proagro.model.OrderDetails
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class BuyAgainAdapter(
    private val buyAgainProductNames: MutableList<String>,
    private val buyAgainProductPrice: MutableList<String>,
    private val buyAgainProductImage: MutableList<String>,
    private var listOfOrderItem: MutableList<OrderDetails> = mutableListOf(),
    private var requireContext: Context,
    private val itemClicked: onItemClicked
) : RecyclerView.Adapter<BuyAgainAdapter.BuyAgainViewHolder>() {


    interface onItemClicked {
        fun onItemClickListner(position: Int)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuyAgainViewHolder {
        val binding =
            BuyAgainItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BuyAgainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BuyAgainViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = buyAgainProductNames.size

    inner class BuyAgainViewHolder(private val binding: BuyAgainItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                itemClicked.onItemClickListner(adapterPosition)

            }
        }

        fun bind(position: Int) {

            val orderDetails = listOfOrderItem[position]

            binding.BuyAgainProductName.text = buyAgainProductNames[position]
            binding.BuyAgainProductPrice.text = buyAgainProductPrice[position]
            val uriString: String = buyAgainProductImage[position]
            val uri: Uri = Uri.parse(uriString)
            Glide.with(requireContext).load(uri).into(binding.BuyAgainProductImg)

//            binding.orderReceivedBtn.setOnClickListener() {
//                updateOrderStatus()
//            }


            // Check if orderAccepted is true
            if (orderDetails.orderAccepted) {
                // If true, make the button visible
                binding.orderReceivedBtn.visibility = View.VISIBLE
                binding.orderStatusColor.setColorFilter(Color.GREEN)
                // Set a click listener for the button
                binding.orderReceivedBtn.setOnClickListener {
                    // Call the method to update order status
                    Toast.makeText(requireContext,"Order Received Successfully!!",Toast.LENGTH_SHORT).show()
                    updateOrderStatus(orderDetails)
                }
            } else {
                // If orderAccepted is false, make the button invisible
                binding.orderReceivedBtn.visibility = View.INVISIBLE
                binding.orderStatusColor.setColorFilter(Color.GRAY)
            }

        }

        private fun updateOrderStatus(orderDetails: OrderDetails) {
            val userEmailOfClickedItem = listOfOrderItem[position].userEmail
            val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference
            //  var listOfOrderItem: MutableList<OrderDetails> = mutableListOf()
            val itemPushKey = listOfOrderItem[position].itemPushKey
            val completeOrderRefrence =
                databaseReference.child("CompletedOrder").child(itemPushKey!!)
            completeOrderRefrence.child("orderReceived").setValue(true)

            // update in History
            val buyHistoryRefrence = databaseReference.child("user").child(userEmailOfClickedItem!!)
                .child("BuyHistory").child(itemPushKey!!)
            buyHistoryRefrence.child("orderReceived").setValue(true)
        }

    }
}

