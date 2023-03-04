package com.inlay.concertswatcher.presentation.favouriteList.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.inlay.concertswatcher.data.models.ConcertItemNetworkModel
import com.inlay.concertswatcher.presentation.ext.asDetailsData
import com.inlay.details.data.models.DetailsDataModel

class AppFavouriteListViewModel : FavouriteListViewModel() {

    private val _favouriteConcerts = MutableLiveData<List<ConcertItemNetworkModel>>()
    override val favouriteConcerts: LiveData<List<ConcertItemNetworkModel>> = _favouriteConcerts

    private val _itemNetworkModelMutableLiveData = MutableLiveData<DetailsDataModel?>()
    override val itemNetworkModelMutableLiveData: LiveData<DetailsDataModel?> =
        _itemNetworkModelMutableLiveData

    override fun updateConcertsList(concertsList: List<ConcertItemNetworkModel>) {
        _favouriteConcerts.value = concertsList
    }

    override fun goToDetails(itemData: ConcertItemNetworkModel?) {
        _itemNetworkModelMutableLiveData.value = itemData?.asDetailsData()
    }

    override fun setItemDataToNull() {
        _itemNetworkModelMutableLiveData.value = null
    }
}