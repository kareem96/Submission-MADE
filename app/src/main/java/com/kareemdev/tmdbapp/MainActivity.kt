package com.kareemdev.tmdbapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.kareemdev.tmdbapp.databinding.ActivityMainBinding
import com.kareemdev.tmdbapp.presentation.home.HomeFragment
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = title
        setSupportActionBar(binding.appBarLayout.toolbar)
        navigationChange(HomeFragment())

        binding.bottomNavigation.setOnNavigationItemSelectedListener(navSelectedListen)

    }

    private fun navigationChange(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frameContainer, fragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit()
    }

    private val navSelectedListen = BottomNavigationView.OnNavigationItemSelectedListener{

        when(it.itemId){
            R.id.home -> {
                navigationChange(HomeFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.favorite -> {
                moveToFavoriteFragment()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun moveToFavoriteFragment() {
        val fragment = instastateFragment()
        Log.d( "moveToFavoriteFragment", fragment.toString())
        if(fragment != null){
            navigationChange(fragment)
        }
    }

    private fun instastateFragment(): Fragment? {
        return try {
            Class.forName("com.kareemdev.tmdbapp.favorite.presentation.FavoriteFragment").newInstance() as Fragment
        }catch (e: Exception){
            Toast.makeText(this, "Module not found", Toast.LENGTH_SHORT).show()
            null
        }
    }

}