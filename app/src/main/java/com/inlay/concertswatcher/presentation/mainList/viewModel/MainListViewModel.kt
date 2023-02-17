package com.inlay.concertswatcher.presentation.mainList.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.inlay.concertswatcher.data.models.ConcertItemNetworkModel
import com.inlay.concertswatcher.data.models.ConcertsDataNetworkModel
import com.inlay.details.data.models.DetailsDataModel

abstract class MainListViewModel : ViewModel() {
    abstract val concertsData: LiveData<ConcertsDataNetworkModel>
    abstract val error: LiveData<String>
    abstract val isLoading: LiveData<Boolean>

    abstract val itemNetworkModelMutableLiveData: LiveData<DetailsDataModel?>

    abstract fun addTempConcerts(concertsDataNetworkModel: ConcertsDataNetworkModel)

    abstract fun goToDetails(itemData: ConcertItemNetworkModel?)

    abstract fun initConcertsData()

    abstract fun setItemModelToNull()
}