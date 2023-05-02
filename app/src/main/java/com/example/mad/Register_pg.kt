package com.example.mad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mad.databinding.ActivityRegisterPgBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class Register_pg : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterPgBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterPgBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.goToLogin.setOnClickListener {
            val intent = Intent(this, LoginNew::class.java)
            startActivity(intent)
        }

        binding.btnRegister.setOnClickListener {
            val name = binding.inputName.text.toString()
            val phone = binding.inputMobile.text.toString()
            val address = binding.inputAddress.text.toString()
            val email = binding.inputEmail.text.toString()
            val pass = binding.inputPassword.text.toString()

            if (name.isNotEmpty() && phone.isNotEmpty() && address.isNotEmpty() && email.isNotEmpty() && pass.isNotEmpty()) {
                firebaseAuth.createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val user = firebaseAuth.currentUser
                            val uid = user?.uid
                            val db = FirebaseDatabase.getInstance().reference
                            db.child("users").child(uid!!).setValue(
                                hashMapOf(
                                    "name" to name,
                                    "phone" to phone,
                                    "address" to address,
                                    "email" to email
                                )
                            ).addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show()
                                    finish()
                                } else {
                                    Toast.makeText(this, "Registration Failed", Toast.LENGTH_SHORT).show()
                                }
                            }
                        } else {
                            Toast.makeText(this, "Registration Failed", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Empty Fields are not allowed", Toast.LENGTH_SHORT).show()
            }

        }
    }
}