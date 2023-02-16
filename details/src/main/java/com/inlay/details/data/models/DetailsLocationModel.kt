package com.inlay.details.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailsLocationModel(
    val type: String?,
    val detailsAddressModel: DetailsAddressModel,
    val detailsGeoModel: DetailsGeoModel,
    val name: String
) : Parcelable