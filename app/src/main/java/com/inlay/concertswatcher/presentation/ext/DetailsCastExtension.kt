package com.inlay.concertswatcher.presentation.ext

import com.inlay.concertswatcher.data.models.ConcertAddressNetworkModel
import com.inlay.concertswatcher.data.models.ConcertGeoNetworkModel
import com.inlay.concertswatcher.data.models.ConcertItemNetworkModel
import com.inlay.concertswatcher.data.models.ConcertPerformerNetworkModel
import com.inlay.details.data.models.*

fun ConcertItemNetworkModel.asDetailsData(): DetailsDataModel {
    return DetailsDataModel(
        context,
        type,
        description,
        endDate,
        eventStatus,
        image,
        concertLocationNetworkModel.asDetailsLocation(),
        name,
        concertPerformerNetworkModel.castListPerformers(),
        startDate
    )
}

fun com.inlay.concertswatcher.data.models.ConcertLocationNetworkModel.asDetailsLocation(): DetailsLocationModel {
    return DetailsLocationModel(
        type,
        concertAddressNetworkModel.asDetailsAddress(),
        concertGeoNetworkModel.asDetailsGeo(),
        name
    )
}

fun ConcertAddressNetworkModel.asDetailsAddress(): DetailsAddressModel {
    return DetailsAddressModel(
        type, addressCountry, addressLocality, postalCode, streetAddress
    )
}

fun ConcertGeoNetworkModel.asDetailsGeo(): DetailsGeoModel {
    return DetailsGeoModel(type, latitude, longitude)
}

fun List<ConcertPerformerNetworkModel>.castListPerformers(): List<DetailsPerformerModel> {
    val detailsListDetailsPerformerModels = ArrayList<DetailsPerformerModel>()
    this.forEach {
        detailsListDetailsPerformerModels.add(it.asDetailsPerformer())
    }
    return detailsListDetailsPerformerModels
}

fun ConcertPerformerNetworkModel.asDetailsPerformer(): DetailsPerformerModel {
    return DetailsPerformerModel(type, name)
}



