//===================== old code
//package com.example.proagro.fragments
//
//import android.content.Intent
//import android.graphics.Color
//import android.net.Uri
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Toast
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//import com.example.proagro.activities.RecentOrderProducts
//import com.example.proagro.adapters.BuyAgainAdapter
//import com.example.proagro.databinding.FragmentHistoryBinding
//import com.example.proagro.model.OrderDetails
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.database.DataSnapshot
//import com.google.firebase.database.DatabaseError
//import com.google.firebase.database.DatabaseReference
//import com.google.firebase.database.FirebaseDatabase
//import com.google.firebase.database.Query
//import com.google.firebase.database.ValueEventListener
//import java.io.Serializable
//
//class Fragment_history : Fragment() {
//
//    private lateinit var binding: FragmentHistoryBinding
//
//    private lateinit var buyAgainAdapter: BuyAgainAdapter
//
//    private lateinit var auth: FirebaseAuth
//    private lateinit var database: FirebaseDatabase
//    private lateinit var userEmail: String
//    private lateinit var hashedEmail: String
//    private var listOfOrderItem: MutableList<OrderDetails> = mutableListOf()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        binding = FragmentHistoryBinding.inflate(inflater, container, false)
////====================================================
//        // intialize firebase & database
//        auth = FirebaseAuth.getInstance()
//        database = FirebaseDatabase.getInstance()
//        // current user email intialize
//        userEmail = auth.currentUser?.email.toString() ?: ""
//        hashedEmail = userEmail.replace('.', ',')
////====================================================
//        // retrieve and display user order history
//        retrieveBuyHistory()
//
//// recent buy item click and go to next activity for details
//        binding.recentBuyItem.setOnClickListener() {
//            if (listOfOrderItem.isNotEmpty()) {
//                val intent = Intent(requireContext(), RecentOrderProducts::class.java)
//                intent.putExtra("RecentBuyOrderItemList", listOfOrderItem as Serializable)
//                startActivity(intent)
//            } else {
//                Toast.makeText(requireContext(), "List empty", Toast.LENGTH_LONG).show()
//            }
//        }
//
//        binding.receivedBtn.setOnClickListener() {
//            updateOrderStatus()
//        }
//
//
//        return binding.root
//    }
//
//    private fun updateOrderStatus() {
//        val itemPushKey = listOfOrderItem[0].itemPushKey
//        val completeOrderRefrence = database.reference.child("CompletedOrder").child(itemPushKey!!)
//          completeOrderRefrence.child("paymentReceived").setValue(true)
//    }
//
//
//    // function of retrieve and display user order history
//    private fun retrieveBuyHistory() {
//        binding.recentBuyItem.visibility = View.INVISIBLE
//
//        val buyItemRefrence: DatabaseReference =
//            database.reference.child("user").child(hashedEmail).child("BuyHistory")
//        val shortingQuery: Query = buyItemRefrence.orderByChild("currentTime")
//
//        shortingQuery.addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                for (butSnapshot in snapshot.children) {
//                    val buyHistoryItem: OrderDetails? =
//                        butSnapshot.getValue(OrderDetails::class.java)
//                    buyHistoryItem?.let {
//                        listOfOrderItem.add(it)
//                    }
//                    // listOfOrderItem.reverse()
//                    if (listOfOrderItem.isNotEmpty()) {
//                        setDataInRecentBuyItem()
//                        setPreviousBuyItemRecyclerView()
//                    }
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//
//            }
//        })
//    }
//
//
//    //set user data in recent buy
//    private fun setDataInRecentBuyItem() {
//        binding.recentBuyItem.visibility = View.VISIBLE
//        val recentOrderItem: OrderDetails? = this.listOfOrderItem.firstOrNull()
//        recentOrderItem?.let {
//            with(binding) {
//                BuyAgainProductName.text = it.productNames?.firstOrNull() ?: ""
//                BuyAgainProductPrice.text = it.productPrices?.firstOrNull() ?: ""
//                val image: String = it.productImages?.firstOrNull() ?: ""
//                val uri: Uri? = Uri.parse(image)
//                Glide.with(requireContext()).load(uri).into(BuyAgainProductImg)
//
//                val isOrderIsAccepted = listOfOrderItem[0].orderAccepted
//                if (isOrderIsAccepted) {
//                    orderStatus.background.setTint(Color.GREEN)
//                    receivedBtn.visibility = View.VISIBLE
//
//                }
//
//            }
//        }
//    }
//
//    // set previous buy product into recycler view => recent buy
//    private fun setPreviousBuyItemRecyclerView() {
//        val buyAgainProductName: MutableList<String> = mutableListOf<String>()
//        val buyAgainProductPrice: MutableList<String> = mutableListOf<String>()
//        val buyAgainProductImage: MutableList<String> = mutableListOf<String>()
//        for (i: Int in 1 until listOfOrderItem.size) {
//            listOfOrderItem[i].productNames?.firstOrNull()?.let { buyAgainProductName.add(it) }
//            listOfOrderItem[i].productPrices?.firstOrNull()?.let { buyAgainProductPrice.add(it) }
//            listOfOrderItem[i].productImages?.firstOrNull()?.let { buyAgainProductImage.add(it) }
//        }
//        val rv: RecyclerView = binding.buyAgainRecyclerView
//        rv.layoutManager = LinearLayoutManager(requireContext())
//        buyAgainAdapter = BuyAgainAdapter(
//            buyAgainProductName,
//            buyAgainProductPrice,
//            buyAgainProductImage,
//            requireContext()
//        )
//        rv.adapter = buyAgainAdapter
//    }
//}
//==========================old code

// ======= Here start new code =================================================================================

package com.example.proagro.fragments

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proagro.activities.RecentOrderProducts
import com.example.proagro.adapters.BuyAgainAdapter
import com.example.proagro.databinding.FragmentHistoryBinding
import com.example.proagro.model.OrderDetails
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener
import java.io.Serializable

class Fragment_history : Fragment(), BuyAgainAdapter.onItemClicked {

    private lateinit var binding: FragmentHistoryBinding

    private lateinit var buyAgainAdapter: BuyAgainAdapter

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var userEmail: String
    private lateinit var hashedEmail: String
    private var listOfOrderItem: MutableList<OrderDetails> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
//====================================================
        // intialize firebase & database
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        // current user email intialize
        userEmail = auth.currentUser?.email.toString() ?: ""
        hashedEmail = userEmail.replace('.', ',')
//====================================================
        // retrieve and display user order history
        retrieveBuyHistory()

        //  setPreviousBuyItemRecyclerView()

        return binding.root
    }


    // function of retrieve and display user order history
    private fun retrieveBuyHistory() {
        // binding.recentBuyItem.visibility = View.INVISIBLE

        val buyItemRefrence: DatabaseReference =
            database.reference.child("user").child(hashedEmail).child("BuyHistory")
        //  val shortingQuery: Query = buyItemRefrence.orderByChild("currentTime")

        buyItemRefrence.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (butSnapshot in snapshot.children) {
                    val buyHistoryItem: OrderDetails? =
                        butSnapshot.getValue(OrderDetails::class.java)
                    buyHistoryItem?.let {
                        listOfOrderItem.add(it)
                    }
                    // Reverse the list
                    listOfOrderItem.reverse()
                    if (listOfOrderItem.isNotEmpty()) {
                        // setDataInRecentBuyItem()
                        setPreviousBuyItemRecyclerView()
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    private fun setPreviousBuyItemRecyclerView() {
        val buyAgainProductName: MutableList<String> = mutableListOf<String>()
        val buyAgainProductPrice: MutableList<String> = mutableListOf<String>()
        val buyAgainProductImage: MutableList<String> = mutableListOf<String>()

        for (orderItem: OrderDetails in listOfOrderItem) {
            // Add data to respective lists for populating the RecyclerView
            orderItem.productNames?.firstOrNull()?.let { buyAgainProductName.add(it) }
            orderItem.productPrices?.firstOrNull()?.let { buyAgainProductPrice.add(it) }
            orderItem.productImages?.firstOrNull()?.let { buyAgainProductImage.add(it) }
        }

        // Set up RecyclerView
        val rv: RecyclerView = binding.buyAgainRecyclerView
        rv.layoutManager = LinearLayoutManager(requireContext())

        // Create the adapter and set it to the RecyclerView
        val adapter = BuyAgainAdapter(
            buyAgainProductName,
            buyAgainProductPrice,
            buyAgainProductImage,
            listOfOrderItem,
            requireContext(), this
        )
        rv.adapter = adapter
    }


    override fun onItemClickListner(position: Int) {
        val intent = Intent(requireContext(), RecentOrderProducts::class.java)
        val userOrderDetails: OrderDetails? = listOfOrderItem[position]

        // Check if userOrderDetails is not null before passing it through the intent
        userOrderDetails?.let {
            val orderList = ArrayList<OrderDetails>()
            orderList.add(it)
            intent.putExtra("RecentBuyOrderItemList", orderList as Serializable)
            startActivity(intent)
        } ?: run {
            Toast.makeText(requireContext(), "Selected order details are null", Toast.LENGTH_LONG)
                .show()
        }
    }


}

