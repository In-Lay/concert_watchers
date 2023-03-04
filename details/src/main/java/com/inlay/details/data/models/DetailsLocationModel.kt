package com.inlay.details.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailsLocationModel(
    val type: String? = "",
    @SerializedName("address")
    val detailsAddressModel: DetailsAddressModel = DetailsAddressModel(),
    @SerializedName("geo")
    val detailsGeoModel: DetailsGeoModel = DetailsGeoModel(),
    val name: String = ""
) : Parcelable