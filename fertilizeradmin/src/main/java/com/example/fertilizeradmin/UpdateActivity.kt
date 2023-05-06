package com.example.fertilizeradmin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.fertilizeradmin.databinding.ActivityAddBinding
import com.example.fertilizeradmin.databinding.ActivityUpdateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.updateButton.setOnClickListener{
            val referenceFert = binding.referenceFert.text.toString()
            val updateQuantity  = binding.updateQuantity.text.toString()
            val updatePrice = binding.updatePrice.text.toString()

            updateData(referenceFert,updateQuantity,updatePrice)

        }
    }

    private fun updateData(fertID:String ,quantity:String, price:String){
        databaseReference = FirebaseDatabase.getInstance().getReference("Fertilizer Directory")
        val fertilizer = mapOf<String,String>("quantity" to quantity,"price" to price)
        databaseReference.child(fertID).updateChildren(fertilizer).addOnSuccessListener {
            binding.referenceFert.text.clear()
            binding.updateQuantity.text.clear()
            binding.updatePrice.text.clear()
            Toast.makeText(this,"Updated",Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Toast.makeText(this,"Unable to update",Toast.LENGTH_SHORT).show()
        }
    }


}