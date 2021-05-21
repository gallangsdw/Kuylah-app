package com.stackoverthink.kuylahapp.api

import com.stackoverthink.kuylahapp.response.DummyRequest
import com.stackoverthink.kuylahapp.response.DummyResponse
import com.stackoverthink.kuylahapp.response.ItineraryRequest
import com.stackoverthink.kuylahapp.response.ItineraryResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @POST("/api/users")
    fun postItinerary(
            @Body req: DummyRequest
    ): Call<DummyResponse>
}