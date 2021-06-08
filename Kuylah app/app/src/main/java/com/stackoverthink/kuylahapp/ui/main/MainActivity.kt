package com.stackoverthink.kuylahapp.ui.main

import android.R.layout
import android.app.ActionBar
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Constraints
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.stackoverthink.kuylahapp.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.bottom_nav)
        val navController = findNavController(R.id.fragment)


        //Ganti custom action bar
        var title: TextView = findViewById(R.id.tv_title)
        title.text = getString(R.string.home)

        supportActionBar?.hide()
        navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.label){
                "Beranda" -> {
                    Log.d("Hello", getString(R.string.home))
                    var title: TextView = findViewById(R.id.tv_title)
                    title.text = getString(R.string.home)

                    val fragment: View = findViewById(R.id.fragment)
                    val params = fragment.layoutParams as ViewGroup.MarginLayoutParams
                    params.topMargin = 130
                    fragment.layoutParams = params
                }
                "Rencana Perjalanan" ->{
                    var title: TextView = findViewById(R.id.tv_title)
                    title.text = getString(R.string.my_itinerary)
                    val fragment: View = findViewById(R.id.fragment)
                    val params = fragment.layoutParams as ViewGroup.MarginLayoutParams
                    params.topMargin = 130
                    fragment.layoutParams = params
                }
                "Profile" ->{
                    Log.d("Hello", getString(R.string.setting))
                    var title: TextView = findViewById(R.id.tv_title)
                    title.text = getString(R.string.setting)
                    val fragment: View = findViewById(R.id.fragment)
                    val params = fragment.layoutParams as ViewGroup.MarginLayoutParams
                    params.topMargin = 130
                    fragment.layoutParams = params
                }
                "Itinerary Detail Fragment"->{
                    Log.d("Hello", getString(R.string.my_itinerary))
                    var title: TextView = findViewById(R.id.tv_title)
                    title.text = getString(R.string.my_itinerary)
                    val fragment: View = findViewById(R.id.fragment)
                    val params = fragment.layoutParams as ViewGroup.MarginLayoutParams
                    params.topMargin = 130
                    fragment.layoutParams = params
                }
                "Destination Fragment" ->{
                    val fragment: View = findViewById(R.id.fragment)
                    val params = fragment.layoutParams as ViewGroup.MarginLayoutParams
                    params.topMargin = 0
                    fragment.layoutParams = params
                }

            }
        }
    }

}