package com.stackoverthink.kuylahapp.api

import com.stackoverthink.kuylahapp.data.response.PostResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.POST

interface ApiService {
    @POST("api")
    fun postItinerary(
            @Field("id") id: String,
            @Field("title") title: String,
            @Field("day") day: String,
            @Field("budget") budget: String,
            @Field("category") category: String
    ): Call<PostResponse>
}