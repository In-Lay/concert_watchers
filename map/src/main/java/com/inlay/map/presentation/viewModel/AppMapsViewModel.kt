package com.inlay.map.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import com.inlay.map.data.MapGeoModel

class AppMapsViewModel : MapsViewModel() {
    private val _mapsMapGeoModel = MutableLiveData<MapGeoModel?>()
    override val mapsMapGeoModel: MutableLiveData<MapGeoModel?> = _mapsMapGeoModel

    private val _onMapsFocused = MutableLiveData<Boolean>()
    override val onMapsFocused: MutableLiveData<Boolean> = _onMapsFocused


    override fun setMapsFocus(isFocused: Boolean) {
        _onMapsFocused.value = isFocused
    }

    override fun addGeo(mapGeoModel: MapGeoModel?) {
        _mapsMapGeoModel.value = mapGeoModel
    }
}