package com.example.tenantfinderapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.tenantfifnderapp.R

class HouseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_house)
        var houses = intent.getParcelableExtra<HousesData>("title")
        if (houses != null){
            val textView : TextView = findViewById(R.id.houseActivityTv)
            val imageView : ImageView = findViewById(R.id.houseActivityIv)
            textView.text = houses.title
            imageView.setImageResource(houses.image)
        }
    }
}