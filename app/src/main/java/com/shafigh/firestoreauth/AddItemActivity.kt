package com.shafigh.firestoreauth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AddItemActivity : AppCompatActivity() {
    private lateinit var db : FirebaseFirestore
    private lateinit var textField : EditText
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)

        db = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()

        textField = findViewById(R.id.editText)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener{
            saveItem()
        }
    }
    private  fun saveItem(){

        val user = auth.currentUser

        val item = Item(textField.text.toString(),false,"mat")
        db.collection("users").document(user!!.uid).collection("Item").add(item)
            .addOnSuccessListener {
                println("Item Added")
            }
            .addOnFailureListener{
                println("Item not added")
            }
    }
}
