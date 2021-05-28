package com.stackoverthink.kuylahapp.response

import com.google.gson.annotations.SerializedName

data class ScheduleResponse(

    @field:SerializedName("schedule")
    val destination: List<ListItineraryResponse>
)
