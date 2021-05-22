package com.stackoverthink.kuylahapp.ui.main.itinerary

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.stackoverthink.kuylahapp.models.Itinerary

class ItineraryViewModel : ViewModel() {

    companion object {
        private val TAG = ItineraryViewModel::class.java.simpleName
    }

    val listItineraries = MutableLiveData<ArrayList<Itinerary>>()

    fun setItineraries(){
        val listItnerary = ArrayList<Itinerary>()
        val db = Firebase.firestore
        db.collection("users/${FirebaseAuth.getInstance().uid}/itineraries")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val itinerary = Itinerary()
                    Log.d(TAG, "${document.id} => ${document.data}")
                    itinerary.title = document.getString("title")
                    itinerary.day = document.getString("day")
                    itinerary.budget = document.getString("budget")
                    itinerary.category = document.get("category") as MutableList<String>?
                    listItnerary.add(itinerary)
                }

                listItineraries.postValue(listItnerary)
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }
    }

    fun getListItineraries(): LiveData<ArrayList<Itinerary>>{
        return listItineraries
    }
}