package com.stackoverthink.kuylahapp.ui.main.itinerary.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.getField
import com.google.firebase.ktx.Firebase
import com.stackoverthink.kuylahapp.models.Destination
import com.stackoverthink.kuylahapp.models.Schedule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ItineraryDetailViewModel : ViewModel() {

    companion object {
        private val TAG = ItineraryDetailViewModel::class.java.simpleName
    }

    private val listSchedules = MutableLiveData<ArrayList<Schedule>>()


    fun setData(itineraryTitle: String, day: String){
        CoroutineScope(IO).launch {
            requestData(itineraryTitle, day)
        }
    }

    private suspend fun requestData(itineraryTitle: String, day: String){
        val listSchedule = getScheduleFromDatabase(itineraryTitle, day)
        setScheduleOnMainThread(listSchedule)
    }

    private fun setSchedules(listSchedule: ArrayList<Schedule>){
        listSchedules.postValue(listSchedule)
    }

    private suspend fun setScheduleOnMainThread(listSchedule: ArrayList<Schedule>) {
        withContext(Main){
            setSchedules(listSchedule)
        }
    }

    private suspend fun getScheduleFromDatabase(itineraryTitle: String, day: String): ArrayList<Schedule>{
        val listSchedule = ArrayList<Schedule>()
        val db = Firebase.firestore
        for (i in 1..day.toInt()){
            val schedule = Schedule()
            val listDestination = ArrayList<Destination>()
            db.collection("users/${FirebaseAuth.getInstance().uid}/itineraries/$itineraryTitle/schedule/$i/destination")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result){
                        val destination = Destination()
                        Log.d("Response", "${document.id} => ${document.data}")
                        destination.name = document.getString("name")
                        destination.price = document.getString("price")
                        listDestination.add(destination)
                    }
                    Log.d("Listttt Destination", "$listDestination")
                }
            schedule.schedules = listDestination
            listSchedule.add(schedule)
        }
        delay(1000)
        return listSchedule
    }

    fun getListDestinations() : LiveData<ArrayList<Schedule>>{
        return listSchedules
    }
}