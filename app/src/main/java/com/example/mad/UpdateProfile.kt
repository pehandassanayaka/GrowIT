package com.example.mad

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.view.LayoutInflater
import android.view.Window
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import com.example.mad.databinding.ActivityUpdateProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.protobuf.Value
import java.io.File

class UpdateProfile : AppCompatActivity() {

    private lateinit var binding : ActivityUpdateProfileBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference       //database reference
    private lateinit var storageReference: StorageReference         //storage reference
    private lateinit var storageRef: FirebaseStorage
    private lateinit var dialog: Dialog
    private lateinit var user: User
    private lateinit var uid: String
    private lateinit var uri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //getting current users user id
        auth = FirebaseAuth.getInstance()
        uid = auth.currentUser?.uid.toString()

        //getting database reference
        databaseReference = FirebaseDatabase.getInstance().getReference("users")

        //image uri implementation
        val imageView = binding.profImg
        val galleryImage = registerForActivityResult(
            ActivityResultContracts.GetContent(),
            ActivityResultCallback {
                imageView.setImageURI(it)
                if (it != null) {
                    uri = it
                }
            }
        )

        //initializing storage reference
        storageRef = FirebaseStorage.getInstance()

        binding.profImg.setOnClickListener {
            galleryImage.launch("image/*")
        }

        //getting user data to populate in the EditText
        databaseReference.child(uid).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                user = snapshot.getValue(User::class.java)!!
                binding.edtName.setText(user.name)
                binding.edtEmail.setText(user.email)
                binding.edtPass.setText(user.password)
                binding.edtPhone.setText(user.phone)
                binding.edtAddress.setText(user.address)
                getUserProfilePicture()
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

            //upload image if user has selected a new image
            if(this::uri.isInitialized){
                uploadImage()
            }
        }
    }

    private fun getUserProfilePicture() {
        //find image named with the current uid
        storageReference = FirebaseStorage.getInstance().reference.child("users/$uid")

        //create temporary local file to store the retrieved image
        val localFile = File.createTempFile("tempImage", ".jpg")

        //retrieve image and store it to created temp file
        storageReference.getFile(localFile).addOnSuccessListener {

            //covert temp file to bitmap
            val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)

            //bind image
            binding.profImg.setImageBitmap(bitmap)


        }.addOnFailureListener{
            //Toast.makeText(activity, "Failed to retrieve image", Toast.LENGTH_SHORT).show()
        }
    }

    private fun uploadImage() {

        storageRef.getReference("users").child(auth.currentUser!!.uid)
            .putFile(uri).addOnSuccessListener {
                //Toast.makeText(this, "Account created successfully.", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, "Failed to upload the image.", Toast.LENGTH_SHORT).show()
            }
    }



}
