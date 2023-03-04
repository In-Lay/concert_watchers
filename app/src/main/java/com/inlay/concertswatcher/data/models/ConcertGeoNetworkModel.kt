package com.inlay.concertswatcher.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ConcertGeoNetworkModel(
    val type: String? = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0
) : Parcelable