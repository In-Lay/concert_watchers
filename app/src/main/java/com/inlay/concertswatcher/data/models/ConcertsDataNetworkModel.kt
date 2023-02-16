package com.inlay.concertswatcher.data.models

import com.google.gson.annotations.SerializedName

data class ConcertsDataNetworkModel(
    @SerializedName("data")
    val concertItemsNetworkModel: List<ConcertItemNetworkModel>,
    val page: String
)