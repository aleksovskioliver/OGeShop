package com.example.ogeshop.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ogeshop.ProductDetails
import com.example.ogeshop.R
import com.example.ogeshop.model.Product
import com.squareup.picasso.Picasso

class ProductAdapter(val products: MutableList<Product>) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView
        val photo: ImageView
        val price: TextView

        init {
            title = view.findViewById(R.id.title)
            photo = view.findViewById(R.id.photo)
            price = view.findViewById(R.id.price)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_row, parent, false)
        val holder = ViewHolder(view)
        view.setOnClickListener{
            val intent = Intent(parent.context,ProductDetails::class.java)
            intent.putExtra("title",products[holder.adapterPosition].title)
            parent.context.startActivity(intent)

        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentProduct = products[position]
       // Glide.with(this).load(allProducts[position].photoUrl).into(ivPlayListPicture);

        Picasso.get().load(currentProduct.photoUrl).into(holder.photo)
        holder.title.text = currentProduct.title
        holder.price.text = currentProduct.price.toString()
    }

    override fun getItemCount(): Int {
        return products.size
    }
}