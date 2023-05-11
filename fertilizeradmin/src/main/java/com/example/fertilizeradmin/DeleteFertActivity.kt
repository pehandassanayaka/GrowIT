package com.example.fertilizeradmin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.fertilizeradmin.databinding.ActivityDeleteBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DeleteFertActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDeleteBinding
    private lateinit var databaseReference: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.deleteButton.setOnClickListener{
            val fertID = binding.deleteFert.text.toString()
            if(fertID.isNotEmpty()){
                deleteData(fertID)
            }else{
                Toast.makeText(this,"Please enter fertilizer ID",Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun deleteData(fertID : String){
        databaseReference = FirebaseDatabase.getInstance().getReference("Fertilizer Directory")
        databaseReference.child(fertID).removeValue().addOnSuccessListener {
            binding.deleteFert.text.clear()
            Toast.makeText(this,"Removed",Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Toast.makeText(this,"Unable to remove",Toast.LENGTH_SHORT).show()
        }
    }

}