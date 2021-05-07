package com.stackoverthink.kuylahapp.ui.main

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.stackoverthink.kuylahapp.R

class MainActivity : AppCompatActivity() {

    private var title: String = getString(R.string.home)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.bottom_nav)
        val navController = findNavController(R.id.fragment)

        val titleActionBar: TextView = findViewById(R.id.tv_title)
        titleActionBar.text = title

        navView.setupWithNavController(navController)
    }
}