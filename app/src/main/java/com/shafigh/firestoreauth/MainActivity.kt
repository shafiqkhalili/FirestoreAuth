package com.shafigh.firestoreauth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var textEmail : EditText
    private lateinit var textPassword : EditText

    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textEmail = findViewById(R.id.editTextEmail)
        textPassword = findViewById(R.id.editTextPassword)
        auth = FirebaseAuth.getInstance()

        val currentUser: FirebaseUser? = auth.currentUser

        val createButton = findViewById<Button>(R.id.buttonCreate)

        createButton.setOnClickListener{
            createUser()
        }

        val loginButton = findViewById<Button>(R.id.buttonLogin)
        loginButton.setOnClickListener{
            loginUser()
        }
    }
    private fun createUser(): Unit {
        if (textEmail.text.toString().isEmpty() || textPassword.text.toString().isEmpty())
            return

        auth.createUserWithEmailAndPassword(textEmail.text.toString(),textPassword.text.toString())
            .addOnCompleteListener(this){ task ->
                if (task.isSuccessful){
                    println("!!! User created")
                }else{
                    println("!!! User not created")
                }
            }
    }
    private fun loginUser(): Unit {
        if (textEmail.text.toString().isEmpty() || textPassword.text.toString().isEmpty())
            return

            auth.signInWithEmailAndPassword(textEmail.text.toString(), textPassword.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        goToAddActivity()
                        println("!!! User logedIn")
                    } else {
                        println("!!! User not logedIn")
                    }
                }

    }

    private fun goToAddActivity(): Unit {
        val intent = Intent(this,AddItemActivity::class.java)
        startActivity(intent)
    }
}
