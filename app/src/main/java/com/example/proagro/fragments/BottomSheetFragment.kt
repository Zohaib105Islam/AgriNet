package com.example.proagro.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proagro.adapters.BtmShtProductAdapter
import com.example.proagro.databinding.FragmentBottomSheetBinding
import com.example.proagro.model.ProductItem
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class BottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentBottomSheetBinding

    private lateinit var database: FirebaseDatabase
    private lateinit var productItem: MutableList<ProductItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBottomSheetBinding.inflate(layoutInflater, container, false)

        binding.sheetCloseBtn.setOnClickListener {
            dismiss()
        }

        retrieveProductItem()


        return binding.root
    }

    private fun retrieveProductItem() {

        try {

            database = FirebaseDatabase.getInstance()
            val productRef: DatabaseReference = database.reference.child("product")
            productItem = mutableListOf()

            // fetch data from RealTime database
            productRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    //Clear existing data before show
                    productItem.clear()

                    // loop for through each item
                    for (productSnapshot in snapshot.children) {
                        val menuItem: ProductItem? =
                            productSnapshot.getValue(ProductItem::class.java)
                        menuItem?.let {
                            productItem.add(it)
                        }
                    }
                    // Reverse the order of the lists
                    productItem.reverse()
                    // set data
                    setAdapter()
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "Internet problem !! ${e.message}", Toast.LENGTH_LONG)
                .show()
        }
    }

    private fun setAdapter() {
        val adapter = BtmShtProductAdapter(requireContext(), productItem)
        binding.productRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.productRecyclerView.adapter = adapter
    }

    companion object {

    }

}
