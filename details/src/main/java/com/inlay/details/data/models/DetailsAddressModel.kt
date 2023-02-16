package com.inlay.details.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailsAddressModel(
    val type: String?,
    val addressCountry: String,
    val addressLocality: String,
    val postalCode: String,
    val streetAddress: String
) : Parcelable