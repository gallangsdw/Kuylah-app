package com.stackoverthink.kuylahapp.response

import com.google.gson.annotations.SerializedName

data class ItineraryRequest(
        @field:SerializedName("title")
        var title: String? = null,

        @field:SerializedName("day")
        var day: String? = null,

        @field:SerializedName("budget")
        var budget: String? = null,

        //@field:SerializedName("category")
        //val category: String,
)
