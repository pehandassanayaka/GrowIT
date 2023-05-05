package com.example.mad

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.LayoutInflater
import android.view.Window
import android.widget.Button
import com.example.mad.databinding.ActivityUserProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.StorageReference
import com.google.protobuf.Value

class UserProfile : AppCompatActivity() {

    private lateinit var binding : ActivityUserProfileBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference       //database reference
    private lateinit var storageReference: StorageReference         //storage reference
    private lateinit var dialog: Dialog
    private lateinit var user: User
    private lateinit var uid: String
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

        binding.edtProf.setOnClickListener{
            val intent = Intent(this, UpdateProfile::class.java)
            startActivity(intent)
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
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

}