package com.stackoverthink.kuylahapp.api

import com.stackoverthink.kuylahapp.response.ItineraryRequest
import com.stackoverthink.kuylahapp.response.ItineraryResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("api")
    fun postItinerary(
            @Body req: ItineraryRequest
    ): Call<ItineraryResponse>
}