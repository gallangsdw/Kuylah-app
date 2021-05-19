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
import com.stackoverthink.kuylahapp.ui.models.Destination
import com.stackoverthink.kuylahapp.ui.models.Itinerary

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

        add()

        //val appBarConfiguration = AppBarConfiguration(setOf(R.id.homeFragment, R.id.itineraryFragment, R.id.settingFragment))
        //setupActionBarWithNavController(navController, appBarConfiguration)

        supportActionBar?.hide()

        navView.setupWithNavController(navController)
    }

    private fun addItineraryToDatabase(
        dataItineraries: Itinerary?,
        dataSchedules: ArrayList<MutableLiveData<Destination>>,
        day: String
    ) {
        val db = Firebase.firestore
        db.collection("users/${FirebaseAuth.getInstance().uid}/itineraries").document(dataItineraries!!.title.toString())
            .set(dataItineraries!!)
            .addOnSuccessListener {
                Log.d("Itinerary Added Succeed", ": ${dataItineraries.toString()}")
            }
            .addOnFailureListener {
                Log.w("Itinerary Added Failed", "Error adding document", it)
            }

        //Adding the itinerary details
        for (position in dataSchedules.indices){
            scheduleField["day"] = day
            db.document("users/${FirebaseAuth.getInstance().uid}/itineraries/${dataItineraries.title.toString()}/schedule/Day 2").set(scheduleField)
            db.document("users/${FirebaseAuth.getInstance().uid}/itineraries/${dataItineraries.title.toString()}/schedule/Day 2/destination/${dataSchedules[position].value!!.name}")
                .set(dataSchedules[position].value!!)
                .addOnSuccessListener {
                    Log.d("Itinerary Added Succeed", ": ${dataSchedules[position].value}")
                }
                .addOnFailureListener {
                    Log.w("Itinerary Added Failed", "Error adding document", it)
                }
        }
    }

    private fun add() {
        dataItineraries.value = Itinerary(
            title = "Akhirnya ke jogja lagi",
            day = "3",
            budget = "3000000",
            category = arrayListOf("Beach", "Natural")
        )
        dataSchedule1.value = Destination(name = "Nasi Goreng", price = "30000")
        dataSchedules.add(dataSchedule1)
        dataSchedule2.value = Destination(name = "Parangtritis", price = "20000")
        dataSchedules.add(dataSchedule2)
        dataSchedule3.value = Destination(name = "UGM", price = "50000")
        dataSchedules.add(dataSchedule3)
        addItineraryToDatabase(dataItineraries.value, dataSchedules, "1")
    }
}