package com.inlay.concertswatcher.presentation.mainList

import com.inlay.concertswatcher.data.Address
import com.inlay.concertswatcher.data.Data
import com.inlay.concertswatcher.data.Geo
import com.inlay.concertswatcher.data.Performer
import com.inlay.details.data.Location

fun Data.asDetailsData(): com.inlay.details.data.Data {
    return com.inlay.details.data.Data(
        context,
        type,
        description,
        endDate,
        eventStatus,
        image,
        location.asDetailsLocation(),
        name,
        performer.castListPerformers(),
        startDate
    )
}

fun com.inlay.concertswatcher.data.Location.asDetailsLocation(): Location {
    return Location(type, address.asDetailsAddress(), geo.asDetailsGeo(), name)
}

fun Address.asDetailsAddress(): com.inlay.details.data.Address {
    return com.inlay.details.data.Address(
        type, addressCountry, addressLocality, postalCode, streetAddress
    )
}

fun Geo.asDetailsGeo(): com.inlay.details.data.Geo {
    return com.inlay.details.data.Geo(type, latitude, longitude)
}

fun List<Performer>.castListPerformers(): List<com.inlay.details.data.Performer> {
    val detailsListPerformers = ArrayList<com.inlay.details.data.Performer>()
    this.forEach {
        detailsListPerformers.add(it.asDetailsPerformer())
    }
    return detailsListPerformers
}

fun Performer.asDetailsPerformer(): com.inlay.details.data.Performer {
    return com.inlay.details.data.Performer(type, name)
}



