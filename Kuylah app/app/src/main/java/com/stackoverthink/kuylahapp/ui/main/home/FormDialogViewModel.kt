package com.stackoverthink.kuylahapp.ui.main.home

import android.util.Log
import androidx.lifecycle.ViewModel
import com.stackoverthink.kuylahapp.api.ApiConfig
import com.stackoverthink.kuylahapp.response.ItineraryRequest
import com.stackoverthink.kuylahapp.response.ItineraryResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FormDialogViewModel : ViewModel() {

    fun postItinerary(itineraryRequest: ItineraryRequest) {

        val client = ApiConfig.getApiService().postItinerary(itineraryRequest)
        client.enqueue(object : Callback<ItineraryResponse> {
            override fun onResponse(call: Call<ItineraryResponse>, response: Response<ItineraryResponse>) {
                val itinerary = response.body()
                Log.d("success", itinerary.toString())
            }

            override fun onFailure(call: Call<ItineraryResponse>, t: Throwable) {
                Log.e("failed", t.message.toString())
            }

        })
    }
}