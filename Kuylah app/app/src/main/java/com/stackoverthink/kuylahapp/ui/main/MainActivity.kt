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

        prepare()
//        addItem()

//        addScheduleToDatabase(dataSchedule.value, "2")

        //val appBarConfiguration = AppBarConfiguration(setOf(R.id.homeFragment, R.id.itineraryFragment, R.id.settingFragment))
        //setupActionBarWithNavController(navController, appBarConfiguration)

        supportActionBar?.hide()

        navView.setupWithNavController(navController)
    }

    private fun addScheduleToDatabase(destination: Destination?, day: String) {
        scheduleField["day"] = day
        val db = Firebase.firestore
        db.collection("users/${FirebaseAuth.getInstance().uid}/itineraries").add(scheduleField)
        db.collection("users/${FirebaseAuth.getInstance().uid}/itineraries/schedule")
            .add(destination!!)
            .addOnSuccessListener {
                Log.d("Itinerary Added Succeed", ": ${destination.toString()}")
            }
            .addOnFailureListener {
                Log.w("Itinerary Added Failed", "Error adding document", it)
            }
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
//
//        scheduleField["day"] = day
//        db.document("users/${FirebaseAuth.getInstance().uid}/itineraries/${dataItineraries.title.toString()}/schedule/Day 2").set(scheduleField)
//        db.document("users/${FirebaseAuth.getInstance().uid}/itineraries/${dataItineraries.title.toString()}/schedule/Day 2/destination/${dataSchedules!!.name}")
//            .set(dataSchedules!!)
//            .addOnSuccessListener {
//                Log.d("Itinerary Added Succeed", ": $dataSchedule")
//            }
//            .addOnFailureListener {
//                Log.w("Itinerary Added Failed", "Error adding document", it)
//            }
    }

    private fun prepare(){
        dataItineraries.value = Itinerary(title="Ga jadi ke jogja", day = "3", budget = "3000000",category = arrayListOf("Beach", "Natural"))
        dataSchedule1.value = Destination(name = "Nasi Goreng", price = "30000")
        dataSchedules.add(dataSchedule1)
        dataSchedule2.value = Destination(name = "Parangtritis", price = "20000")
        dataSchedules.add(dataSchedule2)
        dataSchedule3.value = Destination(name = "UGM", price = "50000")
        dataSchedules.add(dataSchedule3)
        addItineraryToDatabase(dataItineraries.value, dataSchedules, "2")


//        dataItineraries.value = Itinerary(title="Ke jogja lagi akhirnya", day = "2", budget = "3000000",category = arrayListOf("what", "the"), schedule = null)

//        dataTitles = arrayOf(
//            "Jalan2 ke jogja for the first time!",
//            "Ke jogja lagi akhirnya",
//            "Jogjaaaaa",
//            "Terpaksa ke jogja",
//            "Ke jogja lagiii",
//            "Jogja lur"
//        )
//
//        days = arrayOf(
//            "3 D",
//            "2 D",
//            "1 D",
//            "2 D",
//            "2 D",
//            "2 D"
//        )
//
//        dataBudgets = arrayOf(
//            "Rp500.000",
//            "Rp400.000",
//            "Rp800.000",
//            "Rp120.000",
//            "Rp50.000",
//            "Rp60.000"
//        )
//
//        categories = arrayOf(
//            mutableListOf("Beach", "Natural"),
//            mutableListOf("Beach", "Natural", "Cultural and Historical"),
//            mutableListOf("Beach", "Natural"),
//            mutableListOf("Beach", "Natural"),
//            mutableListOf("Beach", "Natural"),
//            mutableListOf("Beach", "Natural", "Museum", "Art Gallery and Exhibition")
//        )
//
//        destinationName = arrayOf(
//            "Kopi Klotok",
//            "Bukit Klangon Merapi",
//            "Situs Warung Boto",
//        )
//
//        destinationTime = arrayOf(
//            "07:00 AM - 09:00 AM",
//            "10:00 AM - 12:00 PM",
//            "01:00 PM - 03:00 PM"
//        )
//
//
//        for (position in destinationName.indices){
//            val destinationData = Destination(
//                destinationName[position],
//                destinationTime[position]
//            )
//            destinations.value
//        }
//
//        val Day = Day(
//            1,
//            destinations
//        )
//
//        schedules.add(Day)


    }

    private fun addItem(){
//        for (position in dataTitles.indices){
//            val itinerary = Itinerary(
//                dataTitles[position],
//                days[position],
//                dataBudgets[position],
//                categories[position],
//                schedules
//            )
//            dataItineraries.add(itinerary)
//        }
    }
}