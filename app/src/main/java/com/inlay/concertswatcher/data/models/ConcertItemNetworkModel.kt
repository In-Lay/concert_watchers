package com.inlay.concertswatcher.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ConcertItemNetworkModel(
    val context: String?,
    val type: String?,
    val description: String,
    val endDate: String,
    val eventStatus: String,
    val image: String,
    @SerializedName("location")
    val concertLocationNetworkModel: ConcertLocationNetworkModel,
    val name: String,
    @SerializedName("performer")
    val concertPerformerNetworkModel: List<ConcertPerformerNetworkModel>,
    val startDate: String
) : Parcelable