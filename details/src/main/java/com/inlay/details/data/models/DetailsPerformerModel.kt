package com.inlay.details.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailsPerformerModel(
    val type: String?, val name: String
) : Parcelable