package com.example.tenantfinderapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.tenantfifnderapp.R

class MainActivity : AppCompatActivity() {
    lateinit var EdtLandlord:TextView
    lateinit var EdtTenant:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        EdtLandlord = findViewById(R.id.medtlandlord)
        EdtTenant = findViewById(R.id.medttenant)

        EdtLandlord.setOnClickListener{
            startActivity(Intent(this,loginActivity::class.java))
        }
        EdtTenant.setOnClickListener {
            startActivity(Intent(this,SearchhousesActivity::class.java))
        }

    }
}