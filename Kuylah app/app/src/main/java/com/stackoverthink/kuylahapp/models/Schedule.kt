package com.stackoverthink.kuylahapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Schedule (
        var schedules: ArrayList<Destination>? = null
) : Parcelable
