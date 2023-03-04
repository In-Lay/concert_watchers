package com.inlay.concertswatcher.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ConcertLocationNetworkModel(
    val type: String? = "",
    @SerializedName("address")
    val concertAddressNetworkModel: ConcertAddressNetworkModel = ConcertAddressNetworkModel(),
    @SerializedName("geo")
    val concertGeoNetworkModel: ConcertGeoNetworkModel = ConcertGeoNetworkModel(),
    val name: String = ""
) : Parcelable