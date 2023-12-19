package com.example.proagro.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import com.example.proagro.R
import com.example.proagro.adapters.PopularAdapter
import com.example.proagro.databinding.FragmentHomeBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class Fragment_home : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)

//        binding.viewProductBtn.setOnClickListener{
//            val bottomSheetDialog = BottomSheetFragment()
//            bottomSheetDialog.show(parentFragmentManager,"Test")
//        }
        binding.viewProductBtn.setOnClickListener {
            val bottomSheetDialog =BottomSheetFragment()
            bottomSheetDialog.show(parentFragmentManager,"Test")
        }


        val imageList = ArrayList<SlideModel>() // Create image list
        imageList.add(SlideModel(R.drawable.sliderimage1,ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.sliderimage2,ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.sliderimage3,ScaleTypes.FIT))

        var imageSlider=binding.imageSlider
        imageSlider.setImageList(imageList)

        imageSlider.setItemClickListener(object : ItemClickListener{
            override fun doubleClick(position: Int) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(position: Int) {
                val itemPosition=imageList[position]
                val itemMessage="Selected item $position"
                Toast.makeText(requireContext(),itemMessage,Toast.LENGTH_SHORT).show()
            }
        })

        val productName= listOf("Vertako","Glapho","Cartep","Lemda","Vertako","Glapho","Cartep","Lemda","Vertako","Glapho","Cartep","Lemda","Vertako","Glapho","Cartep","Lemda")
        val productPrice= listOf("$15","$7","$10","$20","$15","$7","$10","$20","$15","$7","$10","$20","$15","$7","$10","$20")
        val productImage= listOf(R.drawable.spray1,R.drawable.spary2,R.drawable.sprey3,R.drawable.spray4,R.drawable.spray1,R.drawable.spary2,R.drawable.sprey3,R.drawable.spray4,R.drawable.spray1,R.drawable.spary2,R.drawable.sprey3,R.drawable.spray4,R.drawable.spray1,R.drawable.spary2,R.drawable.sprey3,R.drawable.spray4)

        val adapter=PopularAdapter(productName,productPrice,productImage,requireContext())
        binding.popularRecyclerView.layoutManager=LinearLayoutManager(requireContext())
         binding.popularRecyclerView.adapter=adapter


            return binding.root




    }
}
