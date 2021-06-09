package com.stackoverthink.kuylahapp.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiConfig {
    companion object{
        fun getApiService(): ApiService {
            val loggingInterceptor =
                    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .build()
            val gson = GsonBuilder()
                .setLenient()
                .create()
            val retrofit = Retrofit.Builder()
                    .baseUrl("http://34.101.197.40/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}