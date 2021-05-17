package com.stackoverthink.kuylahapp.data.response

import com.google.gson.annotations.SerializedName

data class PostResponse(
        @field:SerializedName("title")
        val title: String,

        @field:SerializedName("day")
        val day: String,

        @field:SerializedName("budget")
        val budget: String,

        @field:SerializedName("category")
        val category: String,
)
