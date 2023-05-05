package com.example.mad

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.LayoutInflater
import android.view.Window
import android.widget.Button
import android.widget.Toast
import com.example.mad.databinding.ActivityUpdateProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.StorageReference
import com.google.protobuf.Value

class UpdateProfile : AppCompatActivity() {

    private lateinit var binding : ActivityUpdateProfileBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference       //database reference
    private lateinit var storageReference: StorageReference         //storage reference
    private lateinit var dialog: Dialog
    private lateinit var user: User
    private lateinit var uid: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //getting current users user id
        auth = FirebaseAuth.getInstance()
        uid = auth.currentUser?.uid.toString()

        //getting database reference
        databaseReference = FirebaseDatabase.getInstance().getReference("users")

        //getting user data to populate in the EditText
        databaseReference.child(uid).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                user = snapshot.getValue(User::class.java)!!
                binding.edtName.setText(user.name)
                binding.edtEmail.setText(user.email)
                binding.edtPass.setText(user.password)
                binding.edtPhone.setText(user.phone)
                binding.edtAddress.setText(user.address)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        //handling the update button click
        binding.updateProf.setOnClickListener {
            val name = binding.edtName.text.toString().trim()
            val email = binding.edtEmail.text.toString().trim()
            val pass = binding.edtPass.text.toString().trim()
            val phone = binding.edtPhone.text.toString().trim()
            val address = binding.edtAddress.text.toString().trim()

            if (name.isEmpty()) {
                binding.edtName.error = "Name required"
                binding.edtName.requestFocus()
                return@setOnClickListener
            }

            if (email.isEmpty()) {
                binding.edtEmail.error = "Email required"
                binding.edtEmail.requestFocus()
                return@setOnClickListener
            }

            if (pass.isEmpty()) {
                binding.edtPass.error = "Email required"
                binding.edtPass.requestFocus()
                return@setOnClickListener
            }

            if (phone.isEmpty()) {
                binding.edtPhone.error = "Phone number required"
                binding.edtPhone.requestFocus()
                return@setOnClickListener
            }

            if (address.isEmpty()) {
                binding.edtAddress.error = "Address required"
                binding.edtAddress.requestFocus()
                return@setOnClickListener
            }

            //creating a map to update user data
            val userMap = HashMap<String, Any>()
            userMap["name"] = name
            userMap["email"] = email
            userMap["password"] = pass
            userMap["phone"] = phone
            userMap["address"] = address

            //updating user data to Firebase Realtime Database
            databaseReference.child(uid).updateChildren(userMap).addOnSuccessListener {
                val intent = Intent(this@UpdateProfile, UserProfile::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener { exception ->
                //displaying error message if any error occurred while updating user data
                Toast.makeText(this@UpdateProfile, exception.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
