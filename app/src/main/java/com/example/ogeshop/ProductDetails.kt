package com.example.ogeshop

import android.content.DialogInterface
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class ProductDetails: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_details)

        val productName: TextView = findViewById(R.id.productName)
        val availability: Button = findViewById<Button>(R.id.availability)


        val title = intent.getStringExtra("title")
        productName.text = title

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