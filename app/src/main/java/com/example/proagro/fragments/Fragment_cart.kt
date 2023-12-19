package com.example.proagro.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proagro.R
import com.example.proagro.activities.PayOutActivity
import com.example.proagro.adapters.CartAdapter
import com.example.proagro.adapters.PopularAdapter
import com.example.proagro.databinding.FragmentCartBinding


class Fragment_cart : Fragment() {

    private lateinit var binding: FragmentCartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCartBinding.inflate(inflater, container, false)

        binding.proceedBtn.setOnClickListener{
            val intent=Intent(requireContext(),PayOutActivity::class.java)
            startActivity(intent)
            requireActivity().finish()

        }

        val cartName= listOf("Vertako","Glapho","Cartep","Lemda","Vertako","Glapho","Cartep","Lemda","Vertako","Glapho","Cartep","Lemda","Vertako","Glapho","Cartep","Lemda")
        val cartPrice= listOf("$15","$7","$10","$20","$15","$7","$10","$20","$15","$7","$10","$20","$15","$7","$10","$20")
        val cartImage= listOf(
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

        val adapter= CartAdapter(ArrayList(cartName),ArrayList(cartPrice),ArrayList(cartImage))
        binding.cartRecyclerView.layoutManager= LinearLayoutManager(requireContext())
        binding.cartRecyclerView.adapter=adapter


        return binding.root
    }

}