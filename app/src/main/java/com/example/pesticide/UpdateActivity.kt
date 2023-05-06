package com.example.pesticide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.pesticide.databinding.ActivityUpdateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.UpdateButton.setOnClickListener {
            val referenceName = binding.referenceName.text.toString()
            val updatePrice = binding.UpdatePrice.text.toString()
            val updateExpDate = binding.updateExpDate.text.toString()
            val updateVolume = binding.updateVolume.text.toString()

            updateData(referenceName, updatePrice, updateExpDate, updateVolume)
        }
    }

    private fun updateData(name: String, price: String, expDate: String, volume: String){
        databaseReference = FirebaseDatabase.getInstance().getReference("Pesticide Directory")
        val pesticides = mapOf( "name" to name, "price" to  price, "expDate" to expDate, "volume" to volume)
        databaseReference.child(name).updateChildren(pesticides).addOnSuccessListener {
            binding.referenceName.text.clear()
            binding.UpdatePrice.text.clear()
            binding.updateExpDate.text.clear()
            binding.updateVolume.text.clear()

            Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Unable to update", Toast.LENGTH_SHORT).show()
        }
    }
}