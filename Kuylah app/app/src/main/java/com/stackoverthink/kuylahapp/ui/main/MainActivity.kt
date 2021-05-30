package com.stackoverthink.kuylahapp.ui.main

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.stackoverthink.kuylahapp.R
import com.stackoverthink.kuylahapp.models.Destination
import com.stackoverthink.kuylahapp.models.Itinerary

class MainActivity : AppCompatActivity() {
    private lateinit var dataTitles: Array<String>
    private lateinit var days: Array<String>
    private lateinit var dataBudgets: Array<String>
    private lateinit var categories: Array<MutableList<String>>
//    private var schedules = MutableLiveData<Day>()
    private lateinit var destinationName: Array<String>
    private lateinit var destinationTime: Array<String>
    private var destinations = MutableLiveData<Destination>()
    private var dataItineraries = MutableLiveData<Itinerary>()

    private var dataSchedule1 = MutableLiveData<Destination>()
    private var dataSchedule2 = MutableLiveData<Destination>()
    private var dataSchedule3 = MutableLiveData<Destination>()
    private var dataSchedules = ArrayList<MutableLiveData<Destination>>()

    private var scheduleField = mutableMapOf("day" to "0")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.bottom_nav)
        val navController = findNavController(R.id.fragment)

        var title: TextView = findViewById(R.id.tv_title)
        title.text = getString(R.string.home)

        //val appBarConfiguration = AppBarConfiguration(setOf(R.id.homeFragment, R.id.itineraryFragment, R.id.settingFragment))
        //setupActionBarWithNavController(navController, appBarConfiguration)

        supportActionBar?.hide()

        navView.setupWithNavController(navController)
    }
}