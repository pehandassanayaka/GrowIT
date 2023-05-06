package com.example.growit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.growit.databinding.ActivityUploadBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.lang.ref.PhantomReference

class UploadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUploadBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener{
            val crop = binding.uploadName.text.toString()
            val planting = binding.pDate.text.toString()
            val harvesting = binding.hDate.text.toString()
            val land = binding.area.text.toString()

            databaseReference = FirebaseDatabase.getInstance().getReference("Crop Details")
            val users = UserData(crop, planting, harvesting, land)
            databaseReference.child(crop).setValue(users).addOnSuccessListener {
                binding.uploadName.text.clear()
                binding.pDate.text.clear()
                binding.hDate.text.clear()
                binding.area.text.clear()

                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()

                val intent = Intent( this@UploadActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener{
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}