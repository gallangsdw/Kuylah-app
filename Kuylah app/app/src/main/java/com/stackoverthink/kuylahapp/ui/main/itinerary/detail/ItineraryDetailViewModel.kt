package com.stackoverthink.kuylahapp.ui.main.itinerary.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.stackoverthink.kuylahapp.ui.models.Destination

class ItineraryDetailViewModel : ViewModel() {

    companion object {
        private val TAG = ItineraryDetailViewModel::class.java.simpleName
    }

    val listDestinations = MutableLiveData<ArrayList<Destination>>()

    fun setDestinations(itineraryTitle: String, day: String){
        val listDestination = ArrayList<Destination>()
        val db = Firebase.firestore

        db.collection("users/${FirebaseAuth.getInstance().uid}/itineraries/$itineraryTitle/schedule/$day/destination")
            .get()
            .addOnSuccessListener { result ->
                for (document in result){
                    val destination = Destination()
                    Log.d(TAG, "$day and ${document.id} => ${document.data}")
                    destination.name = document.getString("name")
                    destination.price = document.getString("price")
                    listDestination.add(destination)
                }

                listDestinations.postValue(listDestination)
            }

    }

    fun getListDestinations() : LiveData<ArrayList<Destination>>{
        return listDestinations
    }
}