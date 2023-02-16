package com.inlay.concertswatcher.presentation.mainList.viewModel.item

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.inlay.concertswatcher.data.models.ConcertItemNetworkModel

abstract class ItemViewModel : ViewModel() {
    abstract val textName: LiveData<String>
    abstract val textLocation: LiveData<String>
    abstract val textDate: LiveData<String>
    abstract val imageUrl: LiveData<String>

    abstract val itemNetworkModelMutableLiveData: LiveData<ConcertItemNetworkModel?>

    abstract fun goToDetails()

    abstract fun initItemData(itemData: ConcertItemNetworkModel?)
}