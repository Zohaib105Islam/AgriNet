package com.example.proagro.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proagro.R
import com.example.proagro.adapters.BtmShtProductAdapter
import com.example.proagro.adapters.CartAdapter
import com.example.proagro.databinding.FragmentBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.NonDisposableHandle.parent

class BottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentBottomSheetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentBottomSheetBinding.inflate(layoutInflater, container,false)

        binding.sheetCloseBtn.setOnClickListener{
            dismiss()
        }


        val productName= listOf("Vertako","Glapho","Cartep","Lemda","Vertako","Glapho","Cartep","Lemda","Vertako","Glapho","Cartep","Lemda","Vertako","Glapho","Cartep","Lemda")
        val productPrice= listOf("$15","$7","$10","$20","$15","$7","$10","$20","$15","$7","$10","$20","$15","$7","$10","$20")
        val productImage= listOf(
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

        val adapter= BtmShtProductAdapter(ArrayList(productName),ArrayList(productPrice),ArrayList(productImage),requireContext())
        binding.productRecyclerView.layoutManager= LinearLayoutManager(requireContext())
        binding.productRecyclerView.adapter=adapter



        return binding.root
    }

    companion object {

            }

    }
