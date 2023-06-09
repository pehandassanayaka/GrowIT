package com.example.mad.UserManagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mad.databinding.ActivityLoginNewBinding
import com.google.firebase.auth.FirebaseAuth

class LoginNew : AppCompatActivity() {

    private lateinit var binding: ActivityLoginNewBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginNewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.goToRegister.setOnClickListener{
            val intent = Intent(this, Register_pg::class.java)
            startActivity(intent)
        }
        binding.backBtnLog.setOnClickListener{
            finish() // finish the current activity to go back to the previous one
        }

        binding.btnLogin.setOnClickListener{
            val email = binding.loginInputEmail.text.toString()
            val pass = binding.inputPassword.text.toString()

            if(email.isNotEmpty() && pass.isNotEmpty()){
                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if(it.isSuccessful){
                        val intent = Intent(this, Dashboard::class.java)
                        startActivity(intent)
                    }
                    else{
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                    }
            }
            else{
                Toast.makeText(this, "Empty Fields are not allowed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onStart(){
        super.onStart()
        if(firebaseAuth.currentUser != null){
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }
    }
}