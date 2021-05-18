package com.stackoverthink.kuylahapp.ui.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Destination(
    var name: String? = "",
    var price: String? = ""
) : Parcelable