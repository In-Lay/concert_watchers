package com.inlay.details.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailsLocationModel(
    val type: String? = "",
    val detailsAddressModel: DetailsAddressModel = DetailsAddressModel(),
    val detailsGeoModel: DetailsGeoModel = DetailsGeoModel(),
    val name: String = ""
) : Parcelable