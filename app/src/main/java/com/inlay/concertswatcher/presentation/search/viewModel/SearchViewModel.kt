package com.inlay.concertswatcher.presentation.search.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.inlay.concertswatcher.data.models.ConcertsDataNetworkModel
import com.inlay.concertswatcher.presentation.search.models.SearchUiModel

abstract class SearchViewModel : ViewModel() {
    abstract val searchConcertsData: LiveData<ConcertsDataNetworkModel>
    abstract val searchError: LiveData<String>
    abstract val searchIsLoading: LiveData<Boolean>

    abstract val onDatePickerClickedFlag: LiveData<Boolean>

    abstract val onDatePickerClosedFlag: LiveData<Boolean>

    abstract fun sendSearchUiModel(uiModel: SearchUiModel)

    abstract fun addTempConcerts(concertsDataNetworkModel: ConcertsDataNetworkModel)

    abstract fun openDateDialog()

    abstract fun onDialogClosed()
}