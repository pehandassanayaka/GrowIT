package com.example.mad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mad.databinding.ActivityDashboardBinding
import com.example.mad.databinding.ActivityUserProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class Dashboard : AppCompatActivity() {
    private lateinit var binding : ActivityDashboardBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference       //database reference
    private lateinit var uid: String
    private lateinit var user: User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.profIcon.setOnClickListener{
            val intent = Intent(this, UserProfile::class.java)
            startActivity(intent)
        }
        binding.weatherLayout.setOnClickListener{
            val intent = Intent(this, Weather::class.java)
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
    }
    private fun getUserData(){
        databaseReference.child(uid).addValueEventListener(object : ValueEventListener {
            //user details will get to here
            override fun onDataChange(snapshot: DataSnapshot) {
                //saving user data to user
                user = snapshot.getValue(User::class.java)!!
                //setting our data to text views
                binding.dashName.setText(user.name)

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}