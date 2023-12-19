package com.example.proagro.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proagro.R
import com.example.proagro.adapters.BuyAgainAdapter
import com.example.proagro.adapters.CartAdapter
import com.example.proagro.databinding.FragmentHistoryBinding

class Fragment_history : Fragment() {

    private lateinit var binding: FragmentHistoryBinding

    private lateinit var buyAgainAdapter:BuyAgainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentHistoryBinding.inflate(inflater,container,false)

        setUpRecyclerView()
        return binding.root
    }

    private fun setUpRecyclerView() {
        val buyAgainProductName= listOf("Vertako","Glapho","Cartep","Lemda","Vertako","Glapho","Cartep","Lemda","Vertako","Glapho","Cartep","Lemda","Vertako","Glapho","Cartep","Lemda")
        val buyAgainProductPrice= listOf("$15","$7","$10","$20","$15","$7","$10","$20","$15","$7","$10","$20","$15","$7","$10","$20")
        val buyAgainProductImage= listOf(
            R.drawable.spray1,
            R.drawable.spary2,
            R.drawable.sprey3,
            R.drawable.spray4,
            R.drawable.spray1,
            R.drawable.spary2,
            R.drawable.sprey3,
            R.drawable.spray4,
            R.drawable.spray1,
            R.drawable.spary2,
            R.drawable.sprey3,
            R.drawable.spray4,
            R.drawable.spray1,
            R.drawable.spary2,
            R.drawable.sprey3,
            R.drawable.spray4)

         buyAgainAdapter= BuyAgainAdapter(ArrayList(buyAgainProductName),ArrayList(buyAgainProductPrice),ArrayList(buyAgainProductImage))
        binding.buyAgainRecyclerView.layoutManager= LinearLayoutManager(requireContext())
        binding.buyAgainRecyclerView.adapter=buyAgainAdapter
    }


}