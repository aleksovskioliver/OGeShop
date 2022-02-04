package com.example.ogeshop

import android.content.DialogInterface
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

class ProductDetails: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_details)

        val productName: TextView = findViewById(R.id.productName)
        val productPhoto: ImageView = findViewById(R.id.photo)
        val availability: Button = findViewById<Button>(R.id.availability)


        val title = intent.getStringExtra("title")
        val photoUrl = intent.getStringExtra("photo_url")
        productName.text = title
        Picasso.get().load(photoUrl).into(productPhoto)

        availability.setOnClickListener {
            AlertDialog.Builder(this)
                .setMessage("Hey $title is in stock")
                .setPositiveButton("OK") { dialog, which ->

                }
                .create()
                .show()
        }
    }
}