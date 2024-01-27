package com.example.proagro.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.proagro.activities.LoginActivity
import com.example.proagro.databinding.FragmentProfileBinding
import com.example.proagro.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class Fragment_profile : Fragment() {

    private lateinit var binding:FragmentProfileBinding

    private val auth =FirebaseAuth.getInstance()
    private val database= FirebaseDatabase.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentProfileBinding.inflate(layoutInflater,container,false)

        binding.logOut.setOnClickListener(){
            auth.signOut()
            startActivity(Intent(requireContext(), LoginActivity::class.java))
            requireActivity().finish()
        }

        setUserData()

        /// edit enabled and disable===========
        binding.profileName.isEnabled=false
        binding.profileEmail.isEnabled=false
        binding.profileAddress.isEnabled=false
        binding.profilePhone.isEnabled=false
        binding.saveInfoBtn.isEnabled = false

        // var isEnable=false
        binding.editBtn.setOnClickListener{
            binding.profileName.isEnabled=! binding.profileName.isEnabled
            binding.profileEmail.isEnabled=! binding.profileEmail.isEnabled
            binding.profileAddress.isEnabled=! binding.profileAddress.isEnabled
            binding.profilePhone.isEnabled=! binding.profilePhone.isEnabled
            binding.saveInfoBtn.isEnabled = ! binding.saveInfoBtn.isEnabled
            binding.profileName.requestFocus()
        }
        ///=====================

        binding.saveInfoBtn.setOnClickListener(){
            try{
            val name : String = binding.profileName.text.toString()
            val address : String = binding.profileAddress.text.toString()
            val email : String = binding.profileEmail.text.toString()
            val phone : String = binding.profilePhone.text.toString()

            updateUserProfileData(name,email,address,phone)
                // disble view
                binding.profileName.isEnabled=false
                binding.profileEmail.isEnabled=false
                binding.profileAddress.isEnabled=false
                binding.profilePhone.isEnabled=false
                binding.saveInfoBtn.isEnabled = false
                //================
        }catch (e : Exception){
                Toast.makeText(requireContext(),"Internet problem !! ${e.message}",Toast.LENGTH_LONG).show()
        }
        }


        return binding.root
    }

    private fun updateUserProfileData(name: String, email: String, address: String, phone: String) {
    try {

        val userEmail:String = auth.currentUser?.email?.toString()?:""
        val hashedEmail = userEmail.replace('.', ',')

        if (hashedEmail != null){

            val userRefrence : DatabaseReference = database.getReference("user").child(hashedEmail)

//            val userData : HashMap<String,String> = hashMapOf(
//                "name" to name,
//                "email" to email,
//                "address" to address,
//                "phone" to phone
//                )

           // val userRefrence = adminRefrence.child(hashedEmail)
            userRefrence.child("name").setValue(name)
            userRefrence.child("email").setValue(email)
            //userRefrence.child("password").setValue(updatePassword)
            userRefrence.child("phone").setValue(phone)
            userRefrence.child("address").setValue(address)

            Toast.makeText(requireContext(),"Profile updated !!",Toast.LENGTH_LONG).show()
            // update email and password in firebase authentication
            auth.currentUser?.updateEmail(email)
           // auth.currentUser?.updatePassword()


//            userRefrence.setValue(userData).addOnSuccessListener {
//                Toast.makeText(requireContext(),"Profile updated successfully !!",Toast.LENGTH_LONG).show()
//            }
//                .addOnFailureListener{
//                    Toast.makeText(requireContext(),"Profile updated Failed !!",Toast.LENGTH_LONG).show()
//                }
        }else{Toast.makeText(requireContext(),"Profile updated Failed !!",Toast.LENGTH_LONG).show()}
    }catch (e : Exception){
        Toast.makeText(requireContext(),"Internet problem !! ${e.message}",Toast.LENGTH_LONG).show()
    }
    }

    private fun setUserData() {

        try {

        val userEmail:String = auth.currentUser?.email?.toString()?:""
        val hashedEmail = userEmail.replace('.', ',')
        if (hashedEmail != null){
            val userRefrence : DatabaseReference = database.getReference("user").child(hashedEmail)

            userRefrence.addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {

                    if (snapshot.exists()){
                        val userProfile : UserModel? = snapshot.getValue(UserModel::class.java)
                        if (userProfile != null){
                            binding.apply {
                                profileName.setText(userProfile.name)
                                profileAddress.setText(userProfile.address)
                                profileEmail.setText(userProfile.email)
                                profilePhone.setText(userProfile.phone)
                            }
                        }
                    }

                }

                override fun onCancelled(error: DatabaseError) {

                }
            })
        }
    }catch (e : Exception){
            Toast.makeText(requireContext(),"Internet problem !! ${e.message}",Toast.LENGTH_LONG).show()
    }
    }


}