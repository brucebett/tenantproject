package com.example.tenantfinderapp

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.tenantfifnderapp.R
import com.google.firebase.auth.FirebaseAuth

class loginActivity : AppCompatActivity() {
    lateinit var Edtemail:EditText
    lateinit var Edtpassword:EditText
    lateinit var Btnlogin:Button
    lateinit var Edtregister:TextView
    lateinit var Edtresetpasssword:TextView
    lateinit var mAuth:FirebaseAuth
    lateinit var progress:ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Edtemail = findViewById(R.id.mtvemail)
        Edtpassword = findViewById(R.id.medtpassword)
        Btnlogin = findViewById(R.id.medtbtnlogin)
        Edtregister = findViewById(R.id.medttxtviewregister)
        Edtresetpasssword = findViewById(R.id.medttxtresetpassword)
        mAuth = FirebaseAuth.getInstance()
        progress = ProgressDialog(this)
        progress.setTitle("Loading")
        progress.setMessage("Please wait...")


        Btnlogin.setOnClickListener {
            var email = Edtemail.text.toString().trim()
            var password = Edtpassword.text.toString().trim()

            if (email.isEmpty()){
                Edtemail.setError("Please fill this input")
                Edtemail.requestFocus()
            }else if(password.isEmpty()){
                Edtpassword.setError("Please fill the input")
                Edtpassword.requestFocus()
            }else{
                // Proceed to register  the user
                progress.show()
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                    progress.dismiss()
                    if (it.isSuccessful){
                        Toast.makeText(this, "Registration successful",
                            Toast.LENGTH_SHORT).show()
                        mAuth.signOut()
                        startActivity(Intent(this, uploadhousesActivity::class.java))
                        finish()

                    }else{
                        displaymessage("Error", it.exception!!.message.toString())
                    }
                }
            }
        }

        Edtregister.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
        }

    }
    fun displaymessage(title:String, message:String){
        var alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle(title)
        alertDialog.setMessage(message)
        alertDialog.setPositiveButton("Ok",null)
        alertDialog.create().show()
    }
    }