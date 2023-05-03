package com.example.mad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firebaseAuth = FirebaseAuth.getInstance()

        val homeLoginBtn = findViewById<Button>(R.id.homeLogin)
        val homeRegBtn = findViewById<Button>(R.id.homeRegister)

        homeLoginBtn.setOnClickListener{
            val intent = Intent(this, LoginNew::class.java)
            startActivity(intent)
        }

        homeRegBtn.setOnClickListener{
            val intent = Intent(this, Register_pg::class.java)
            startActivity(intent)
        }

    }

    override fun onStart(){
        super.onStart()
        if(firebaseAuth.currentUser != null){
            val intent = Intent(this, UserProfile::class.java)
            startActivity(intent)
        }
    }


}