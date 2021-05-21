package com.stackoverthink.kuylahapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Itinerary(
        var title: String? = "",
        var day: String? = "",
        var budget: String? = "",
        var category: MutableList<String>? = null
) : Parcelable



