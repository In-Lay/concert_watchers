package com.inlay.details.data

import com.inlay.details.data.models.DetailsGeoModel

fun DetailsGeoModel.asMapsGeo(): com.inlay.map.data.MapGeoModel {
    return com.inlay.map.data.MapGeoModel(type, latitude, longitude)
}