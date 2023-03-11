package com.inlay.concertswatcher.presentation.search.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.inlay.concertswatcher.data.models.ConcertsDataNetworkModel
import com.inlay.concertswatcher.domain.mainList.GetConcerts
import com.inlay.concertswatcher.domain.mainList.repoes.FlowingConcertsDataRepository
import com.inlay.concertswatcher.presentation.search.models.SearchUiModel
import kotlinx.coroutines.launch

class AppSearchViewModel(
    private val getConcerts: GetConcerts,
    private val dataRepository: FlowingConcertsDataRepository
) : SearchViewModel() {
    private val _searchConcertsData = MutableLiveData<ConcertsDataNetworkModel>()
    override val searchConcertsData: LiveData<ConcertsDataNetworkModel> = _searchConcertsData

    private val _searchError = MutableLiveData<String>()
    override val searchError: LiveData<String> = _searchError

    private val _searchIsLoading = MutableLiveData<Boolean>()
    override val searchIsLoading: LiveData<Boolean> = _searchIsLoading

    private val _onDatePickerClickedFlag = MutableLiveData<Boolean>()
    override val onDatePickerClickedFlag: LiveData<Boolean> = _onDatePickerClickedFlag

    private val _onDatePickerClosedFlag = MutableLiveData<Boolean>()
    override val onDatePickerClosedFlag: LiveData<Boolean> = _onDatePickerClosedFlag

    init {
        Log.d("DetailsFlag", "SearchVM init: searchConcertsData ${searchConcertsData.value}")
    }

    override fun sendSearchUiModel(uiModel: SearchUiModel) {
        _searchIsLoading.value = true
        viewModelScope.launch {
            try {
                val concerts = getConcerts.getConcerts(
                    uiModel.body, uiModel.name, uiModel.minDate, uiModel.maxDate, 1
                )
                _searchIsLoading.postValue(false)
                if (concerts.isSuccessful) {
                    _searchConcertsData.postValue(concerts.body())
                } else {
                    _searchError.postValue(concerts.message())
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun addTempConcerts(concertsDataNetworkModel: ConcertsDataNetworkModel) {
        Log.d(
            "DetailsFlag",
            "SearchVM addTempConcerts; concertsDataNetworkModel: $concertsDataNetworkModel"
        )
        Log.d(
            "DetailsFlag",
            "SearchVM addTempConcerts; searchConcertsData BEFORE postValue: ${searchConcertsData.value}"
        )

        _searchConcertsData.value = concertsDataNetworkModel
        dataRepository.addTempConcerts(_searchConcertsData.value)
        Log.d(
            "DetailsFlag",
            "SearchVM addTempConcerts; searchConcertsData AFTER postValue: ${searchConcertsData.value}"
        )
    }

    override fun changeOnDatePickerClickedFlagToFalse() {
        _onDatePickerClickedFlag.value = false
    }

    override fun openDateDialog() {
        _onDatePickerClickedFlag.postValue(true)
    }

    override fun onDialogClosed() {
        _onDatePickerClosedFlag.value = true
    }
}