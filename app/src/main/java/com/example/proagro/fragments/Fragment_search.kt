package com.example.proagro.fragments

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proagro.adapters.BtmShtProductAdapter
import com.example.proagro.databinding.FragmentSearchBinding
import com.example.proagro.model.ProductItem
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.mancj.materialsearchbar.MaterialSearchBar

class Fragment_search : Fragment() {

    private lateinit var binding: FragmentSearchBinding

    private lateinit var adapter: BtmShtProductAdapter
    private lateinit var database: FirebaseDatabase
    private val originalProducItems = mutableListOf<ProductItem>()

    private var currentProductItems: List<ProductItem> = emptyList()

    private val originalPlaceholder: String =
        "AgriNet" // Change this to your default placeholder text

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        database = FirebaseDatabase.getInstance()

        /// Retrieve product item from database
        retrieveProductItem()


        // Set up the MaterialSearchBar listener
        binding.searchView.setOnSearchActionListener(object :
            MaterialSearchBar.OnSearchActionListener {
            override fun onSearchStateChanged(enabled: Boolean) {
                // Handle search state changes if needed
               // filterProducts(enabled.toString())
                if (!enabled) {
                    // If search is disabled, clear the search
                    clearSearch()
                }
            }

            override fun onSearchConfirmed(text: CharSequence?) {
                // Called when the user submits the search query
                // Check if the entered text is not null or empty
                text?.let {
                    // Handle the search query confirmation
                    filterProducts(it.toString())
                }
            }

            override fun onButtonClicked(buttonCode: Int) {
                // Called when a button in the search bar is clicked
                when (buttonCode) {
                    MaterialSearchBar.BUTTON_BACK -> {
                        // The back button in the search bar is clicked
                        // You can perform any actions or updates when the back button is clicked
                        clearSearch()
                    }

                    MaterialSearchBar.BUTTON_SPEECH -> {
                        // The voice search button is clicked
                        startVoiceRecognition()
                    }
                    // Add more cases for other button codes if needed
                }
            }
        })

        return binding.root
    }

    private fun retrieveProductItem() {
        // refrence to product=>where all product)  node
        val productRefrence: DatabaseReference = database.reference.child("product")

        productRefrence.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (productSnapshot: DataSnapshot in snapshot.children) {

                    val productItem: ProductItem? =
                        productSnapshot.getValue(ProductItem::class.java)
                    productItem?.let { originalProducItems.add(it) }
                }
                currentProductItems = ArrayList(originalProducItems)
                // Reverse the order of the lists
                originalProducItems.reverse()
                showAllProduct()
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

    }

    private fun showAllProduct() {
        val filteredProductItem: ArrayList<ProductItem> = ArrayList(originalProducItems)
        setAdapter(filteredProductItem)
       // setAdapter(currentProductItems)
    }

    private fun setAdapter(filteredProductItem: List<ProductItem>) {
        adapter = BtmShtProductAdapter(requireContext(), filteredProductItem)
        binding.searchProductRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.searchProductRecyclerView.adapter = adapter

    }

    private fun filterProducts(query: String?) {
        val filteredProductItems: List<ProductItem> = if (query.isNullOrBlank()) {
            // If the query is null or empty, show all products
            originalProducItems
        } else {
            // Filter products based on the entered query
            originalProducItems.filter {
                it.productName?.contains(query, ignoreCase = true) == true ||
                        it.productPrice?.contains(query, ignoreCase = true) == true
            }
        }
        currentProductItems = filteredProductItems
        setAdapter(filteredProductItems)
    }

    //==============
    private fun clearSearch() {
        binding.searchView.clearFocus()

        // Reset both the main text and hint (placeholder) text
        binding.searchView.text = ""
        binding.searchView.setPlaceHolder(originalPlaceholder)

        // Reset the product list to show all products
        currentProductItems = ArrayList(originalProducItems)
        setAdapter(currentProductItems)

        // Add log statement to check if the function is being called
        Log.d("ClearSearch", "Search cleared. Product count: ${currentProductItems.size}")

    }

    private fun startVoiceRecognition() {
        // Start the voice recognition process
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )

        try {
            startActivityForResult(intent, REQUEST_VOICE_SEARCH)
        } catch (e: ActivityNotFoundException) {
            // Handle the case where voice recognition is not available on the device
            Toast.makeText(
                requireContext(),
                "Voice recognition is not supported on this device",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    // Handle the result of the voice recognition
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_VOICE_SEARCH && resultCode == Activity.RESULT_OK) {
            val matches = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            if (!matches.isNullOrEmpty()) {
                // Use the first result as the search query
                val voiceQuery = matches[0]

                // Update both the main text and hint (placeholder) text
                binding.searchView.text = voiceQuery
                //   binding.searchView.setPlaceHolder(voiceQuery)
              //  binding.searchView.text = voiceQuery

                // Optionally, you can also initiate the search programmatically
                filterProducts(voiceQuery)
            }
        }
    }

    companion object {
        private const val REQUEST_VOICE_SEARCH = 123
    }

}



