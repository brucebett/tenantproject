package com.example.tenantfinderapp

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.tenantfifnderapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {
    lateinit var Edtemail:EditText
    lateinit var Edtenterpassword:EditText
    lateinit var Edtconfirmpassword:EditText
    lateinit var Btncreate:Button
    lateinit var Edttxtlogin:TextView
    lateinit var mAuth:FirebaseAuth
    lateinit var progress:ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        Edtemail = findViewById(R.id.medtemail)
        Edtenterpassword = findViewById(R.id.medtenterpassword)
        Edtconfirmpassword = findViewById(R.id.medtconfirmpassword)
        Btncreate = findViewById(R.id.mbtncreate)
        Edttxtlogin = findViewById(R.id.medttextviewlogin)
        mAuth = FirebaseAuth.getInstance()
        progress = ProgressDialog(this,)
        progress.setTitle("Loading")
        progress.setMessage("Please wait ...")

   Btncreate.setOnClickListener {
       var email = Edtemail.text.toString().trim()
       var password = Edtenterpassword.text.toString().trim()

       // Check if the user is submitting empty files

       if (email.isEmpty()){
           Edtemail.setError("Please fill this input")
           Edtemail.requestFocus()
       }else if(password.isEmpty()){
           Edtenterpassword.setError("Please fill the input")
           Edtenterpassword.requestFocus()
       }else if(password.length < 6) {
           Edtenterpassword.setError("Password is too short")
           Edtenterpassword.requestFocus()
       }else{
           // Proceed to register  the user
           progress.show()
           mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
               progress.dismiss()
               if (it.isSuccessful){
                   Toast.makeText(this, "Registrationn successful",
                       Toast.LENGTH_SHORT).show()
                   mAuth.signOut()
                   startActivity(Intent(this, loginActivity::class.java))
                   finish()

               }else{
                   displaymessage("Error", it.exception!!.message.toString())
               }
           }
       }
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