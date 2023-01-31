package com.inlay.details.data

data class DetailsData(
    val data: List<Data>,
    val page: String
)

data class Data(
    val context: String?,
    val type: String?,
    val description: String,
    val endDate: String,
    val eventStatus: String,
    val image: String,
    val location: Location,
    val name: String,
    val performer: List<Performer>,
    val startDate: String
)

data class Location(
    val type: String?,
    val address: Address,
    val geo: Geo,
    val name: String
)

data class Performer(
    val type: String?, val name: String
)

data class Address(
    val type: String?,
    val addressCountry: String,
    val addressLocality: String,
    val postalCode: String,
    val streetAddress: String
)

data class Geo(
    val type: String?,
    val latitude: Double,
    val longitude: Double
)