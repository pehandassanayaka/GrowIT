package com.example.growit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.growit.databinding.ActivityMainBinding
import com.example.growit.databinding.ActivityViewBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchButton.setOnClickListener {
            val searchCrop: String = binding.searchCrop.text.toString()
            if (searchCrop.isNotEmpty()){
                readData(searchCrop)
            }else{
                Toast.makeText(this, "Please enter the crop", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun readData(crop: String){
        databaseReference = FirebaseDatabase.getInstance().getReference("Crop Details")
        databaseReference.child(crop).get().addOnSuccessListener{
            if (it.exists()){
                val pDate = it.child("pDate").value
                val hDate = it.child("hDate").value
                val land = it.child("land").value
                Toast.makeText(this, "Result Found", Toast.LENGTH_SHORT).show()
                binding.searchCrop.text.clear()
                binding.readPDate.text = pDate.toString()
                binding.readHDate.text = hDate.toString()
                binding.readLand.text = land.toString()
            }else{
                Toast.makeText(this, "Crop not exist", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
        }
    }
}