package com.stackoverthink.kuylahapp.ui.main.itinerary

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Itinerary(
        var title: String? = "",
        var day: String? = "",
        var budget: String? = "",
        var category: MutableList<String>?,
        var schedule: ArrayList<Day>?
) : Parcelable

@Parcelize
data class Day(
    var id: Int? = 0,
    var destination: ArrayList<Destination>
) : Parcelable

@Parcelize
data class Destination(
        var name: String? = "",
        var time: String? = ""
) : Parcelable