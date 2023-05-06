package com.example.growit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.growit.databinding.ActivityDeleteBinding
import com.example.growit.databinding.ActivityUpdateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DeleteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDeleteBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.deleteButton.setOnClickListener {
            val crop = binding.deleteCrop.text.toString()
            if (crop.isNotEmpty()){
                deleteData(crop)
            }else{
                Toast.makeText(this, "Please enter the crop type", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun deleteData(crop: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Crop Details")
        databaseReference.child(crop).removeValue().addOnSuccessListener {
            binding.deleteCrop.text.clear()
            Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Unable to Delete", Toast.LENGTH_SHORT).show()
        }

    }

}
