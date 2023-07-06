package com.example.tenantfinderapp

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.tenantfifnderapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.IOException

class uploadhousesActivity : AppCompatActivity() {
    lateinit var Edthousesize:EditText
    lateinit var Edthouseprice:EditText
    lateinit var Edtphonenumber:EditText
    lateinit var Edtuploadimg:ImageView
    lateinit var Edthouselocation:EditText
    lateinit var Btnupload:Button
    lateinit var progress: ProgressDialog
    val PICK_IMAGE_REQUEST = 100
    lateinit var filepath: Uri
    lateinit var firebaseStore:FirebaseStorage
    lateinit var storageReference: StorageReference
    lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uploadhouses)
        Edthousesize = findViewById(R.id.medthousesize)
        Edthouseprice = findViewById(R.id.medthouseprice)
        Edtphonenumber = findViewById(R.id.medtphonenumber)
        Edthouselocation = findViewById(R.id.medthuoselocation)
        Edtuploadimg = findViewById(R.id.medtsearchimg)
        Btnupload = findViewById(R.id.mbtnupload)
        progress = ProgressDialog(this)
        progress.setTitle("Uploading...")
        progress.setMessage("PLease Wait...")

        firebaseStore = FirebaseStorage.getInstance()
        storageReference = firebaseStore.getReference()
        mAuth = FirebaseAuth.getInstance()
        //open to select image

        val intent = Intent()
        intent.type = "image/"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(
            Intent.createChooser(intent, "select House Picture"),
            PICK_IMAGE_REQUEST
        )

        Btnupload.setOnClickListener {
            var houseSize = Edthousesize.text.toString().trim()
            var housePrice = Edthouseprice.text.toString().trim()
            var phoneNumber = Edtphonenumber.text.toString().trim()
            var houseLocation = Edthouselocation.text.toString().trim()
            var imageid = System.currentTimeMillis().toString()
            var userid = mAuth.currentUser?.uid
            //check if the user is submitting empty files
            if (houseSize.isEmpty()){
                Edthousesize.setError("Please fill this input")
                Edthousesize.requestFocus()
            }else if (housePrice.isEmpty()){
                Edthouseprice.setError("Please fill this input")
                Edthouseprice.requestFocus()
            }else if (phoneNumber.isEmpty()){
                Edtphonenumber.setError("please fill this input")
                Edtphonenumber.requestFocus()
            }else{
                //Proceed to upload data
                if (filepath == null){
                    Toast.makeText(this, "choose Image", Toast.LENGTH_SHORT).show()

                }else{
                    var ref = storageReference.child("House/$imageid")
                    progress.show()
                    ref.putFile(filepath).addOnCompleteListener{
                        progress.dismiss()
                        if (it.isSuccessful){
                            //proceed to store other data into db
                            ref.downloadUrl.addOnCompleteListener{
                                var imageUrl = it.toString()
                                var houseData = House(houseSize,housePrice,phoneNumber,houseLocation)
                                var dbRef = FirebaseDatabase.getInstance()
                                    .getReference().child("House/$imageid")
                                dbRef.setValue(houseData)
                                Toast.makeText(applicationContext, "Upload successful", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            Toast.makeText(
                                applicationContext,
                                "it.exception!!.message",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK)
            if (data == null || data.data == null){
                return
            }
        filepath = data!!.data!!
        try {
            val bitmap = MediaStore.Images.Media.
            getBitmap(contentResolver,filepath)
            Edtuploadimg.setImageBitmap(bitmap)

        }catch (e: IOException){
            e.printStackTrace()
        }
    }
    }

