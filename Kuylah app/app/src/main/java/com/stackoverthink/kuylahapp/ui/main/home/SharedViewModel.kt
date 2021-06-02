package com.stackoverthink.kuylahapp.ui.main.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stackoverthink.kuylahapp.models.Itinerary

class SharedViewModel : ViewModel() {
    val itinerary = MutableLiveData<Itinerary>()

    fun sendItinerary(itinerary: Itinerary){
        this.itinerary.value = itinerary
    }
}