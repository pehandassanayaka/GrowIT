package com.example.growit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.growit.databinding.ActivityUpdateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CropUpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.updateButton.setOnClickListener {
            val referenceCrop = binding.referenceCrop.text.toString()
            val updatepDate = binding.updatepDate.text.toString()
            val updatehDate = binding.updatehDate.text.toString()
            val updateland = binding.updateland.text.toString()

            updateData(referenceCrop, updatepDate, updatehDate, updateland)
        }
    }
    private fun updateData(land: String, crop: String, pDate: String, hDate: String ){
        databaseReference = FirebaseDatabase.getInstance().getReference("Crop Details")
        val user = mapOf<String, String>("land" to land, "pDate" to pDate, "hDate" to hDate)
        databaseReference.child(crop).setValue(user).addOnSuccessListener {
            binding.referenceCrop.text.clear()
            binding.updatepDate.text.clear()
            binding.updatehDate.text.clear()
            binding.updateland.text.clear()

            Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Unable to Update", Toast.LENGTH_SHORT).show()
           }

    }
}