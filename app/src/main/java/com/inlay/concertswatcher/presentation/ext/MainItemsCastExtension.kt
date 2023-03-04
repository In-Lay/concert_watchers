package com.inlay.concertswatcher.presentation.ext

import com.inlay.concertswatcher.data.models.*
import com.inlay.details.data.models.*

fun DetailsDataModel.asMainItemData(): ConcertItemNetworkModel {
    return ConcertItemNetworkModel(
        context,
        type,
        description,
        endDate,
        eventStatus,
        image,
        detailsLocationModel.asMainItemLocation(),
        name,
        detailsPerformerModel.asMainItemListPerformers(),
        startDate
    )
}

fun DetailsLocationModel.asMainItemLocation(): ConcertLocationNetworkModel {
    return ConcertLocationNetworkModel(
        type,
        detailsAddressModel.asMainItemAddress(),
        detailsGeoModel.asMainItemGeo(),
        name
    )
}

fun DetailsAddressModel.asMainItemAddress(): ConcertAddressNetworkModel {
    return ConcertAddressNetworkModel(
        type, addressCountry, addressLocality, postalCode, streetAddress
    )
}

fun DetailsGeoModel.asMainItemGeo(): ConcertGeoNetworkModel {
    return ConcertGeoNetworkModel(type, latitude, longitude)
}

fun List<DetailsPerformerModel>.asMainItemListPerformers(): List<ConcertPerformerNetworkModel> {
    val detailsListDetailsPerformerModels = ArrayList<ConcertPerformerNetworkModel>()
    this.forEach {
        detailsListDetailsPerformerModels.add(it.asMainListPerformer())
    }
    return detailsListDetailsPerformerModels
}

fun DetailsPerformerModel.asMainListPerformer(): ConcertPerformerNetworkModel {
    return ConcertPerformerNetworkModel(type, name)
}