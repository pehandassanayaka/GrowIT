package com.example.fertilizeradmin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.fertilizeradmin.databinding.ActivityAddBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.lang.ref.PhantomReference

class AddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)











        binding.addButton.setOnClickListener{
            val fertName = binding.addName.text.toString()
            val fertID = binding.addFertId.text.toString()
            val quantity = binding.addQuantity.text.toString()
            val price = binding.addPrice.text.toString()
            val description = binding.addDes.text.toString()

            databaseReference = FirebaseDatabase.getInstance().getReference("Fertilizer Directory")
            val fertilizers = FertilizerData(fertName,fertID,quantity, price,description)
            databaseReference.child(fertID).setValue(fertilizers).addOnSuccessListener {
                binding.addName.text.clear()
                binding.addFertId.text.clear()
                binding.addQuantity.text.clear()
                binding.addPrice.text.clear()
                binding.addDes.text.clear()

                Toast.makeText(this,"Saved",Toast.LENGTH_SHORT).show()
                startActivity(intent)
                finish()


            }.addOnFailureListener{
                Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()

            }



        }




    }



}