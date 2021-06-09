package com.stackoverthink.kuylahapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User (
    var uid: String? = "",
    var email: String? = "",
    var name: String? = "",
    var address: String? = "",
    var photoUrl: String? = "",
    var phoneNumber: String? = ""
) : Parcelable