package com.stackoverthink.kuylahapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Destination(
    var no: Long? = null,

    var nama: String? = null,

    var voteAverage: Double? = null,

    var htmWeekday: Double? = null,

    var description: String? = null,

    var htmWeekend: String? = null,

    var location: String? = null,

    var type: String? = null,

    var voteCount: Double? = null
) : Parcelable