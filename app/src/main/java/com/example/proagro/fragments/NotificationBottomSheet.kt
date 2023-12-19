package com.example.proagro.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proagro.R
import com.example.proagro.adapters.BtmShtProductAdapter
import com.example.proagro.adapters.NotiBtmShtAdapter
import com.example.proagro.databinding.FragmentNotificationBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class NotificationBottomSheet : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentNotificationBottomSheetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentNotificationBottomSheetBinding.inflate(layoutInflater,container,false)


        binding.sheetCloseBtn.setOnClickListener{
            dismiss()
        }

        val notiDesc= listOf("Vertako Vertako Vertako Vertako Vertako Vertako Vertako Vertako Vertako Vertako","Glapho","Cartep","Lemda","Vertako","Glapho","Cartep","Lemda","Vertako","Glapho","Cartep","Lemda","Vertako","Glapho","Cartep","Lemda")
        val notiImage= listOf(
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

        val adapter= NotiBtmShtAdapter(ArrayList(notiDesc),ArrayList(notiImage))
        binding.notificationRecyclerView.layoutManager= LinearLayoutManager(requireContext())
        binding.notificationRecyclerView.adapter=adapter

        return binding.root
    }


}