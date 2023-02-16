package com.inlay.concertswatcher.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ConcertGeoNetworkModel(
    val type: String?,
    val latitude: Double,
    val longitude: Double
) : Parcelable