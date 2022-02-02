package com.example.ogeshop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ogeshop.adapter.ProductAdapter
import com.example.ogeshop.model.Product
import com.google.android.material.navigation.NavigationView

class FirstFragment : Fragment() {

    private lateinit var productRecyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: ProductAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_first,container,false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val products: MutableList<Product> = initList()

        productRecyclerView = view.findViewById(R.id.recycler_view)
        productRecyclerView.layoutManager = GridLayoutManager(activity,2)

        recyclerViewAdapter = ProductAdapter(products)
        productRecyclerView.adapter = recyclerViewAdapter


    }

    private fun initList(): MutableList<Product> {
        return mutableListOf(
            Product("title 1","https://via.placeholder.com/350/ffff00/ff0000",1.19,"some description"),
            Product("title 2","https://via.placeholder.com/350/ffff00/ff0000",1.19,"some description"),
            Product("title 3","https://via.placeholder.com/350/ffff00/ff0000",1.19,"some description"),
            Product("title 4","https://via.placeholder.com/350/ffff00/ff0000",1.19,"some description"),
            Product("title 5","https://via.placeholder.com/350/ffff00/ff0000",1.19,"some description"),
            Product("title 6","https://via.placeholder.com/350/ffff00/ff0000",1.19,"some description"),
            Product("title 7","https://via.placeholder.com/350/ffff00/ff0000",1.19,"some description"),
            Product("title 8","https://via.placeholder.com/350/ffff00/ff0000",1.19,"some description")
        );
    }

}