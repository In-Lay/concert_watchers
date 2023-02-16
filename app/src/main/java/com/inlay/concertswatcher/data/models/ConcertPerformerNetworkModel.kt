package com.inlay.concertswatcher.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ConcertPerformerNetworkModel(
    val type: String?,
    val name: String
) : Parcelable