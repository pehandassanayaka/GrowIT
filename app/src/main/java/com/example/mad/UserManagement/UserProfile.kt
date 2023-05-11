package com.example.mad.UserManagement

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import com.example.mad.databinding.ActivityUserProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File

class UserProfile : AppCompatActivity() {

    private lateinit var binding : ActivityUserProfileBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference       //database reference
    private lateinit var storageReference: StorageReference         //storage reference
    private lateinit var dialog: Dialog
    private lateinit var user: User
    private lateinit var uid: String
    private lateinit var uri: Uri
    private lateinit var storageRef: FirebaseStorage
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.logOutbtn.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, LoginNew::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        //getting current users user id
        auth = FirebaseAuth.getInstance()
        uid = auth.currentUser?.uid.toString()

        //getting database reference
        databaseReference = FirebaseDatabase.getInstance().getReference("users")
        if(uid.isNotEmpty()){
            getUserData()
        }

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

        binding.edtProf.setOnClickListener{
            val intent = Intent(this, UpdateProfile::class.java)
            startActivity(intent)
        }

        binding.deleteProf.setOnClickListener{
            val user = auth.currentUser
            user?.delete()?.addOnCompleteListener{
                if(it.isSuccessful){
                    //account already deleted
                    Toast.makeText(this, "Account successfully deleted!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, Register_pg::class.java)
                    startActivity(intent)
                    //destroy this activity
                    finish()
                }else{
                    //catch error
                    Log.e("error : ", it.exception.toString())
                }
            }
        }
    }

    private fun getUserData(){
        databaseReference.child(uid).addValueEventListener(object : ValueEventListener{
            //user details will get to here
            override fun onDataChange(snapshot: DataSnapshot) {
                //saving user data to user
                user = snapshot.getValue(User::class.java)!!
                //setting our data to text views
                binding.titleNameView.setText(user.name)
                binding.profNameView.setText(user.name)
                binding.profEmailView.setText(user.email)
                binding.profPhoneView.setText(user.phone)
                binding.profAddressView.setText(user.address)
                getUserProfilePicture()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
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

}