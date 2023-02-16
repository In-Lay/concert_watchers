package com.inlay.map.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import com.inlay.map.data.MapGeoModel

class AppMapsViewModel : MapsViewModel() {
    private val _mapsMapGeoModel = MutableLiveData<MapGeoModel?>()
    override val mapsMapGeoModel: MutableLiveData<MapGeoModel?> = _mapsMapGeoModel

    override fun addGeo(mapGeoModel: MapGeoModel?) {
        _mapsMapGeoModel.value = mapGeoModel
    }
}