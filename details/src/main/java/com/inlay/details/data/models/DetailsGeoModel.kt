package com.inlay.details.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailsGeoModel(
    val type: String?, val latitude: Double, val longitude: Double
) : Parcelable