////package com.example.proagro.fragments
////
////import android.os.Bundle
////import android.view.LayoutInflater
////import android.view.View
////import android.view.ViewGroup
////import android.widget.SearchView
////import androidx.fragment.app.Fragment
////import androidx.recyclerview.widget.LinearLayoutManager
////import androidx.recyclerview.widget.RecyclerView
////import com.example.proagro.R
////import com.example.proagro.adapters.BtmShtProductAdapter
////import com.example.proagro.databinding.FragmentSearchBinding
////
////class Fragment_search : Fragment() {
////
////    private lateinit var binding: FragmentSearchBinding
////
////    override fun onCreateView(
////        inflater: LayoutInflater, container: ViewGroup?,
////        savedInstanceState: Bundle?
////    ): View? {
////        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
////
////         val productName = listOf(
////            "Vertako",
////            "Glapho",
////            "Cartep",
////            "Lemda",
////            "Vertako",
////            "Glapho",
////            "Cartep",
////            "Lemda",
////            "Vertako",
////            "Glapho",
////            "Cartep",
////            "Lemda",
////            "Vertako",
////            "Glapho",
////            "Cartep",
////            "Lemda"
////        )
////        val productPrice = listOf(
////            "$15",
////            "$7",
////            "$10",
////            "$20",
////            "$15",
////            "$7",
////            "$10",
////            "$20",
////            "$15",
////            "$7",
////            "$10",
////            "$20",
////            "$15",
////            "$7",
////            "$10",
////            "$20"
////        )
////        val productImage = listOf(
////            R.drawable.spray1,
////            R.drawable.spary2,
////            R.drawable.sprey3,
////            R.drawable.spray4,
////            R.drawable.spray1,
////            R.drawable.spary2,
////            R.drawable.sprey3,
////            R.drawable.spray4,
////            R.drawable.spray1,
////            R.drawable.spary2,
////            R.drawable.sprey3,
////            R.drawable.spray4,
////            R.drawable.spray1,
////            R.drawable.spary2,
////            R.drawable.sprey3,
////            R.drawable.spray4
////        )
////
////        val adapter = BtmShtProductAdapter(
////            ArrayList(productName),
////            ArrayList(productPrice),
////            ArrayList(productImage),
////            requireContext()
////        )
////        binding.searchProductRecyclerView.layoutManager = LinearLayoutManager(requireContext())
////        binding.searchProductRecyclerView.adapter = adapter
////
////        return binding.root
////    }
////
////}
//
//package com.example.proagro.fragments
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.SearchView
//import androidx.fragment.app.Fragment
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.example.proagro.R
//import com.example.proagro.adapters.BtmShtProductAdapter
//import com.example.proagro.databinding.FragmentSearchBinding
//
//class Fragment_search : Fragment() {
//
//    private lateinit var binding: FragmentSearchBinding
//
//    private val productName = listOf(
//        "Vertako",
//        "Glapho",
//        "Cartep",
//        "Lemda",
//        "Vertako",
//        "Glapho",
//        "Cartep",
//        "Lemda",
//        "Vertako",
//        "Glapho",
//        "Cartep",
//        "Lemda",
//        "Vertako",
//        "Glapho",
//        "Cartep",
//        "Lemda"
//    )
//
//    private val productPrice = listOf(
//        "$15",
//        "$7",
//        "$10",
//        "$20",
//        "$15",
//        "$7",
//        "$10",
//        "$20",
//        "$15",
//        "$7",
//        "$10",
//        "$20",
//        "$15",
//        "$7",
//        "$10",
//        "$20"
//    )
//
//    private val productImage = listOf(
//        R.drawable.spray1,
//        R.drawable.spary2,
//        R.drawable.sprey3,
//        R.drawable.spray4,
//        R.drawable.spray1,
//        R.drawable.spary2,
//        R.drawable.sprey3,
//        R.drawable.spray4,
//        R.drawable.spray1,
//        R.drawable.spary2,
//        R.drawable.sprey3,
//        R.drawable.spray4,
//        R.drawable.spray1,
//        R.drawable.spary2,
//        R.drawable.sprey3,
//        R.drawable.spray4
//    )
//
//    private val adapter = BtmShtProductAdapter(
//        ArrayList(productName),
//        ArrayList(productPrice),
//        ArrayList(productImage),
//        requireContext()
//    )
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
//
//        binding.searchProductRecyclerView.layoutManager = LinearLayoutManager(requireContext())
//        binding.searchProductRecyclerView.adapter = adapter
//
//        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                filterProducts(query)
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                filterProducts(newText)
//                return false
//            }
//        })
//
//        return binding.root
//    }
//
//    private fun filterProducts(query: String?) {
//        if (query == null || query.isEmpty()) {
//            // Display all products
//            binding.searchProductRecyclerView.adapter = adapter
//        } else {
//            // Filter products based on query text
//            val filteredProducts = ArrayList<String>()
//            val filteredPrices = ArrayList<String>()
//            val filteredImages = ArrayList<Int>()
//
//            for (i in productName.indices) {
//                if (productName[i].toLowerCase().contains(query.toLowerCase())) {
//                    filteredProducts.add(productName[i])
//                    filteredPrices.add(productPrice[i])
//                    filteredImages.add(productImage[i])
//                }
//            }
//
//            val filteredAdapter = BtmShtProductAdapter(
//                filteredProducts,
//                filteredPrices,
//                filteredImages,
//                requireContext()
//            )
//            binding.searchProductRecyclerView.adapter = filteredAdapter
//        }
//    }
//}
//
//======== Crash Code
//
//package com.example.proagro.fragments
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.SearchView
//import androidx.fragment.app.Fragment
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.example.proagro.R
//import com.example.proagro.adapters.BtmShtProductAdapter
//import com.example.proagro.databinding.FragmentSearchBinding
//import androidx.appcompat.widget.SearchView.OnQueryTextListener
//
//class Fragment_search : Fragment() {
//
//    private lateinit var binding: FragmentSearchBinding
//
//    private val productName = listOf(
//        "Vertako", "Glapho", "Cartep", "Lemda",
//        "Vertako", "Glapho", "Cartep", "Lemda",
//        "Vertako", "Glapho", "Cartep", "Lemda",
//        "Vertako", "Glapho", "Cartep", "Lemda"
//    )
//
//    private val productPrice = listOf(
//        "$15", "$7", "$10", "$20",
//        "$15", "$7", "$10", "$20",
//        "$15", "$7", "$10", "$20",
//        "$15", "$7", "$10", "$20"
//    )
//
//    private val productImage = listOf(
//        R.drawable.spray1, R.drawable.spary2, R.drawable.sprey3, R.drawable.spray4,
//        R.drawable.spray1, R.drawable.spary2, R.drawable.sprey3, R.drawable.spray4,
//        R.drawable.spray1, R.drawable.spary2, R.drawable.sprey3, R.drawable.spray4,
//        R.drawable.spray1, R.drawable.spary2, R.drawable.sprey3, R.drawable.spray4
//    )
//
//    private val adapter = BtmShtProductAdapter(
//        ArrayList(productName),
//        ArrayList(productPrice),
//        ArrayList(productImage),
//        requireContext()
//    )
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = FragmentSearchBinding.inflate(inflater, container, false)
//
//        initializeRecyclerView()
//
//
//                binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
//                    OnQueryTextListener {
//                    override fun onQueryTextSubmit(query: String?): Boolean {
//                        filterProducts(query)
//                        return false
//                    }
//
//                    override fun onQueryTextChange(newText: String?): Boolean {
//                        filterProducts(newText)
//                        return false
//                    }
//                })
//
//
//        return binding.root
//    }
//
//    private fun initializeRecyclerView() {
//        binding.searchProductRecyclerView.layoutManager = LinearLayoutManager(requireContext())
//        binding.searchProductRecyclerView.adapter = adapter
//    }
//
//    private fun filterProducts(query: String?) {
//        if (query == null || query.isEmpty()) {
//            // Display all products
//            binding.searchProductRecyclerView.adapter = adapter
//        } else {
//            // Filter products based on query text
//            val filteredProducts = ArrayList<String>()
//            val filteredPrices = ArrayList<String>()
//            val filteredImages = ArrayList<Int>()
//
//            for (i in productName.indices) {
//                if (productName[i].toLowerCase().contains(query.toLowerCase())) {
//                    filteredProducts.add(productName[i])
//                    filteredPrices.add(productPrice[i])
//                    filteredImages.add(productImage[i])
//                }
//            }
//
//            val filteredAdapter = BtmShtProductAdapter(
//                filteredProducts,
//                filteredPrices,
//                filteredImages,
//                requireContext()
//            )
//            binding.searchProductRecyclerView.adapter = filteredAdapter
//        }
//    }
//}



package com.example.proagro.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proagro.R
import com.example.proagro.adapters.BtmShtProductAdapter
import com.example.proagro.databinding.FragmentSearchBinding

class Fragment_search : Fragment() {

    private lateinit var binding: FragmentSearchBinding

    private val productName = listOf(
        "Vertako", "Glapho", "Cartep", "Lemda",
        "Vertako", "Glapho", "Cartep", "Lemda",
        "Vertako", "Glapho", "Cartep", "Lemda",
        "Vertako", "Glapho", "Cartep", "Lemda"
    )

    private val productPrice = listOf(
        "$15", "$7", "$10", "$20",
        "$15", "$7", "$10", "$20",
        "$15", "$7", "$10", "$20",
        "$15", "$7", "$10", "$20"
    )

    private val productImage = listOf(
        R.drawable.spray1, R.drawable.spary2, R.drawable.sprey3, R.drawable.spray4,
        R.drawable.spray1, R.drawable.spary2, R.drawable.sprey3, R.drawable.spray4,
        R.drawable.spray1, R.drawable.spary2, R.drawable.sprey3, R.drawable.spray4,
        R.drawable.spray1, R.drawable.spary2, R.drawable.sprey3, R.drawable.spray4
    )

    private lateinit var adapter: BtmShtProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        initializeRecyclerView()

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
             androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                filterProducts(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterProducts(newText)
                return false
            }
        })

        return binding.root
    }

    private fun initializeRecyclerView() {
        adapter = BtmShtProductAdapter(
            ArrayList(productName),
            ArrayList(productPrice),
            ArrayList(productImage),
            requireContext()
        )

        binding.searchProductRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.searchProductRecyclerView.adapter = adapter
    }

    private fun filterProducts(query: String?) {
        if (query == null || query.isEmpty()) {
            // Display all products
            binding.searchProductRecyclerView.adapter = adapter
        } else {
            // Filter products based on query text
            val filteredProducts = ArrayList<String>()
            val filteredPrices = ArrayList<String>()
            val filteredImages = ArrayList<Int>()

            for (i in productName.indices) {
                if (productName[i].toLowerCase().contains(query.toLowerCase())) {
                    filteredProducts.add(productName[i])
                    filteredPrices.add(productPrice[i])
                    filteredImages.add(productImage[i])
                }
            }

            for (i in productPrice.indices) {
                if (productPrice[i].toLowerCase().contains(query.toLowerCase())) {
                    filteredProducts.add(productName[i])
                    filteredPrices.add(productPrice[i])
                    filteredImages.add(productImage[i])
                }
            }

            val filteredAdapter = BtmShtProductAdapter(
                filteredProducts,
                filteredPrices,
                filteredImages,
                requireContext()
            )
            binding.searchProductRecyclerView.adapter = filteredAdapter
        }
    }
}


