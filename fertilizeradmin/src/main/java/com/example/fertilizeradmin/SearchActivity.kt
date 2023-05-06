package com.example.fertilizeradmin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.fertilizeradmin.databinding.ActivityMainBinding
import com.example.fertilizeradmin.databinding.ActivitySearchBinding

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.searchButton.setOnClickListener{
            val searchFertilizer:String=binding.searchFertilizer.text.toString()
            if(searchFertilizer.isNotEmpty()){
                readData(searchFertilizer)
            }else{
                Toast.makeText(this,"Please enter the fertilizer ID",Toast.LENGTH_SHORT).show()
            }
        }


    }

    private fun readData(FertID : String){
        databaseReference = FirebaseDatabase.getInstance().getReference("Fertilizer Directory")
        databaseReference.child(FertID).get().addOnSuccessListener {
            if(it.exists()){
                val fertName = it.child("fertName").value
                val quantity = it.child("quantity").value
                val price = it.child("price").value
                val des = it.child("description").value
                Toast.makeText(this,"Results found",Toast.LENGTH_SHORT).show()
                binding.searchFertilizer.text.clear()
                binding.readFertName.text=fertName.toString()
                binding.readQuantity.text=quantity.toString()
                binding.readPrice.text=price.toString()
                binding.readDes.text=des.toString()

            }else{
                Toast.makeText(this,"Fertilizer ID does not exist",Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{
            Toast.makeText(this,"Something went wrong",Toast.LENGTH_SHORT).show()
        }
    }


}