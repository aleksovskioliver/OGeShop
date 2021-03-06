package com.example.ogeshop

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.ogeshop.adapter.CategoriesAdapter
import com.example.ogeshop.adapter.ProductAdapter
import com.example.ogeshop.database.AppDatabase
import com.example.ogeshop.database.ProductFromDatabase
import com.example.ogeshop.model.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FirstFragment : Fragment() {

    private lateinit var productRecyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private lateinit var categoriesRecyclerView: RecyclerView
    private lateinit var categoriesAdapter: CategoriesAdapter
    private lateinit var root: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_first,container,false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //val products: MutableList<Product> = initList()
        val categories = mutableListOf("Books","Movies","CDs")

        productRecyclerView = view.findViewById(R.id.recycler_view)



        val progressBar: ProgressBar = view.findViewById(R.id.progressBar)
        CoroutineScope(Dispatchers.IO).launch {

            val db = Room.databaseBuilder(
                activity!!.applicationContext,
                AppDatabase::class.java,"database-name"
            ).build()

            val productsFromDatabase = db.productDao().getAll()
            val products = productsFromDatabase.map {
                Product(it.title,it.photoUrl,it.price,it.description)
            }.toMutableList()

            withContext(Dispatchers.Main){
                productRecyclerView.layoutManager = GridLayoutManager(activity,2)
                productAdapter = ProductAdapter(products)
                productRecyclerView.adapter = productAdapter
                progressBar.visibility = View.GONE
            }
        }

        categoriesRecyclerView = view.findViewById(R.id.categoriesRecyclerView)
        categoriesRecyclerView.layoutManager =  LinearLayoutManager(activity,RecyclerView.HORIZONTAL,false)

        categoriesAdapter = CategoriesAdapter(categories)
        categoriesRecyclerView.adapter = categoriesAdapter
    }

    private fun initList(): MutableList<Product> {
        return mutableListOf(
            Product("Avatar","https://images-na.ssl-images-amazon.com/images/M/MV5BNzM2MDk3MTcyMV5BMl5BanBnXkFtZTcwNjg0MTUzNA@@._V1_SX1777_CR0,0,1777,999_AL_.jpg",27.33,"A paraplegic marine dispatched to the moon Pandora on a unique mission becomes torn between following his orders and protecting the world he feels is his home."),
            Product("I Am Legend","https://images-na.ssl-images-amazon.com/images/M/MV5BMTIwMDg2MDU4M15BMl5BanBnXkFtZTYwMTA0Nzc4._V1_.jpg",13.33,"Years after a plague kills most of humanity and transforms the rest into monsters, the sole survivor in New York City struggles valiantly to find a cure."),
            Product("Agile Web Development with Rails","http://libgen.org/covers/721000/ae49d9bb94118632df0691df18706319-g.jpg",15.00,"Web Development"),
            Product("Developing Web Apps with Haskell and Yesod: Safety-Driven Web Development","http://libgen.org/covers/1319000/216cf240cc02944b2f77ba17b6a44d49-d.jpg,1.19",12.33,"Web Applications"),
            Product("Web Design for Developers: A Programmer's Guide to Design Tools and Techniques (The Pragmatic Programmers)","http://libgen.org/covers/215000/0644b25822aff6b1def036019c467f52-d.jpg",10.19,"Web Design"),
            Product("PHPEclipse: A User Guide: Take advantage of the leading open source integrated development environment to develop, organize, and debug your PHP web development projects","http://libgen.org/covers/1115000/47dcc653aa3f73886699e93be3415d20-d.jpg",1.19,"PHP developers"),
            Product("300","https://images-na.ssl-images-amazon.com/images/M/MV5BMTMwNTg5MzMwMV5BMl5BanBnXkFtZTcwMzA2NTIyMw@@._V1_SX1777_CR0,0,1777,937_AL_.jpg",16.99,"King Leonidas of Sparta and a force of 300 men fight the Persians at Thermopylae in 480 B.C."),
              Product("The Avengers","https://images-na.ssl-images-amazon.com/images/M/MV5BMTA0NjY0NzE4OTReQTJeQWpwZ15BbWU3MDczODg2Nzc@._V1_SX1777_CR0,0,1777,999_AL_.jpg",33.99,"Earth's mightiest heroes must come together and learn to fight as a team if they are to stop the mischievous Loki and his alien army from enslaving humanity."));
    }

}