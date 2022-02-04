package com.example.ogeshop

import android.os.Bundle
import android.util.Log.d
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.room.Room
import com.example.ogeshop.database.AppDatabase
import com.example.ogeshop.database.ProductFromDatabase
import com.example.ogeshop.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout : DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //binding.root.
        setSupportActionBar(findViewById(R.id.toolbar))
        drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        val navigationView: NavigationView = findViewById(R.id.navigationView)


        CoroutineScope(Dispatchers.IO).launch {

            val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java,"database-name"
            ).build()

            db.productDao().insertAll(ProductFromDatabase(null,"Avatar","https://images-na.ssl-images-amazon.com/images/M/MV5BNzM2MDk3MTcyMV5BMl5BanBnXkFtZTcwNjg0MTUzNA@@._V1_SX1777_CR0,0,1777,999_AL_.jpg",27.33,"A paraplegic marine dispatched to the moon Pandora on a unique mission becomes torn between following his orders and protecting the world he feels is his home."))
            val products = db.productDao().getAll()

            withContext(Dispatchers.Main){
                d("test","products size? ${products.size}")
            }
        }

        navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.actionHome -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.nav_host_fragment_content_main, FirstFragment())
                        .commit()
                }

                R.id.actionBooks -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.nav_host_fragment_content_main, BooksFragment())
                        .commit()
                }
                R.id.actionCDs -> {

                }
                R.id.actionMovies -> {

                }
            }

            it.isChecked = true
            drawerLayout.closeDrawers()
            true
        }
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24)
        }

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)

        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        drawerLayout.openDrawer(GravityCompat.START)
        return true
    }


}