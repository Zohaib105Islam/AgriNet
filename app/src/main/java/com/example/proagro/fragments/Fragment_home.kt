package com.example.proagro.fragments


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proagro.activities.LoginActivity
import com.example.proagro.adapters.BtmShtProductAdapter
import com.example.proagro.databinding.FragmentHomeBinding
import com.example.proagro.model.ProductItem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

class Fragment_home : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private lateinit var database : FirebaseDatabase
    private lateinit var productItems : MutableList<ProductItem>

    val auth= FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)


        binding.logOut.setOnClickListener(){
            auth.signOut()
            startActivity(Intent(requireContext(), LoginActivity::class.java))
            requireActivity().finish()
        }

// Open Bottom Sheet for Notification
        binding.notificationBtn.setOnClickListener {
            val bottomSheetDialog = NotificationBottomSheet()
            bottomSheetDialog.show(parentFragmentManager, "Test")
        }


        binding.viewProductBtn.setOnClickListener {
            val bottomSheetDialog =BottomSheetFragment()
            bottomSheetDialog.show(parentFragmentManager,"Test")
        }



       // set data into slider
        SliderImage()


        //retrieve data
        retrieveProductItem()




            return binding.root

    }

    private fun SliderImage() {
        binding.carouselImageSlider.addData(
            CarouselItem(
                "https://imgscf.slidemembers.com/docs/1/1/878/smart_farming_guide_company_profile_template_design_877744.jpg",
                "Solution for everything"
            )
        )
        binding.carouselImageSlider.addData(
            CarouselItem(
                "https://agricos.net/wp-content/uploads/2015/03/3.jpg",
                "Smart Farmers"
            )
        )
        binding.carouselImageSlider.addData(
            CarouselItem(
                "https://imgscf.slidemembers.com/docs/1/1/683/agriculture_google_slides_to_powerpoint_682961.jpg",
                "Agriculture Company"
            )
        )

    }

    private fun retrieveProductItem() {
        database= FirebaseDatabase.getInstance()
        val productRef: DatabaseReference = database.reference.child("product")
        productItems= mutableListOf()

        // fetch data from RealTime database
        productRef.addListenerForSingleValueEvent(object : ValueEventListener
        {
            override fun onDataChange(snapshot: DataSnapshot) {
                //Clear existing data before show
               // productItem.clear()

                // loop for through each item
                for (productSnapshot in snapshot.children){
                    val menuItem : ProductItem? =productSnapshot.getValue(ProductItem::class.java)
                    menuItem?.let {
                        productItems.add(it)
                    }
                }
                randomPopularItems()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
    private fun randomPopularItems(){

        try {

        // create shuffled list of products
        val index: List<Int> = productItems.indices.toList().shuffled()
        val numItemToShow=6
        val subsetproductItems= index.take(numItemToShow).map { productItems[it] }
        setAdapter(subsetproductItems)
    }catch (e : Exception){
            Toast.makeText(requireContext(),"Internet problem !! ${e.message}",Toast.LENGTH_LONG).show()
    }
    }

    private fun setAdapter(subsetproductItems: List<ProductItem>) {

        val adapter=BtmShtProductAdapter(requireContext(),subsetproductItems)
        binding.popularRecyclerView.layoutManager=LinearLayoutManager(requireContext())
        binding.popularRecyclerView.adapter=adapter
    }

}


