package com.example.mad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class Register_pg : AppCompatActivity() {

     private var mFirebaseAuth = FirebaseAuth.getInstance()
     private var firebaseStore = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_pg)

        val registration_btn = findViewById<TextView>(R.id.btnRegister)
        val goToLoginBtn = findViewById<TextView>(R.id.goToLogin)
        val backButton = findViewById<ImageView>(R.id.backBtnReg)

        goToLoginBtn.setOnClickListener{
            val intent = Intent(this, LoginNew::class.java)
            startActivity(intent)
        }
        backButton.setOnClickListener{
            finish() // finish the current activity to go back to the previous one
        }

        registration_btn.setOnClickListener{
            /**
             * Changing the details to strings after being capturing
             */

            val name = findViewById<EditText>(R.id.inputName).text.toString()
            val phone = findViewById<EditText>(R.id.inputMobile).text.toString()
            val address = findViewById<EditText>(R.id.inputAddress).text.toString()
            val email = findViewById<EditText>(R.id.inputEmail).text.toString()
            val password = findViewById<EditText>(R.id.inputPassword).text.toString()

            /**
             * Creating a user onb firebase
             */

             mFirebaseAuth.createUserWithEmailAndPassword(email, password)
                 .addOnCompleteListener(
                    OnCompleteListener {registrationProcess ->
                        if(registrationProcess.isSuccessful){
                            /**
                             * if the user is create then we can store all the user details on t5he firebase database using a data class user.kt
                             * The users will be stored under a collection called user. This is implemented using the function below
                             * When the user is registered, gets a unique id and this will be stored too
                             */
                            val currentUserId = mFirebaseAuth.currentUser!!.uid
                            val userDetailsToStore = User( currentUserId, name, phone, address, email )

                            //Passing the userDetailsToStore object into the registerUser() function
                            registerUser(userDetailsToStore)
                        }
                    }
                 )

        }



    }

    fun registerUser(userData:User){
        firebaseStore.collection("users") // users is the name of the collection that will be created in firebase
            .document()
            // Using the fields created in the  class with help of UserInfo
            .set(userData).addOnSuccessListener {
                // Displaying a success message to indicate that registration is successful

                Toast.makeText(this,"Registered Successfully",
                    Toast.LENGTH_SHORT
                ).show()
                val intent = Intent(this, LoginNew::class.java)
                startActivity(intent)
            }
            // If the registration fails, Display a message
            .addOnFailureListener { e ->
                Log.e( javaClass.simpleName,
                    "Error while registering.",
                    e
                )
            }
    }
}