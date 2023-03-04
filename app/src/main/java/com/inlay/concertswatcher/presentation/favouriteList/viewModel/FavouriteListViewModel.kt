package com.inlay.concertswatcher.presentation.favouriteList.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.inlay.concertswatcher.data.models.ConcertItemNetworkModel
import com.inlay.details.data.models.DetailsDataModel

abstract class FavouriteListViewModel : ViewModel() {
    abstract val favouriteConcerts: LiveData<List<ConcertItemNetworkModel>>

    abstract val itemNetworkModelMutableLiveData: LiveData<DetailsDataModel?>

    abstract fun updateConcertsList(concertsList: List<ConcertItemNetworkModel>)

    abstract fun goToDetails(itemData: ConcertItemNetworkModel?)

    abstract fun setItemDataToNull()
}