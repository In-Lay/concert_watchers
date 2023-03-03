package com.inlay.details.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailsDataModel(
    val context: String? = "",
    val type: String? = "",
    val description: String = "",
    val endDate: String = "",
    val eventStatus: String = "",
    val image: String = "",
    val detailsLocationModel: DetailsLocationModel = DetailsLocationModel(),
    val name: String = "",
    val detailsPerformerModel: List<DetailsPerformerModel> = listOf(),
    val startDate: String = ""
) : Parcelable

