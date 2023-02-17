package com.inlay.concertswatcher.domain.mainList.repoes

import androidx.lifecycle.MutableLiveData
import com.inlay.concertswatcher.data.models.ConcertsDataNetworkModel

class AppFlowingConcertsDataRepository : FlowingConcertsDataRepository() {

    //TODO use flow(shared flow)
    private val _concertsDataNetwork = MutableLiveData<ConcertsDataNetworkModel?>()
    override val concertsDataNetwork = _concertsDataNetwork

    override fun addTempConcerts(concertsDataNetworkModel: ConcertsDataNetworkModel?) {
        _concertsDataNetwork.value = concertsDataNetworkModel
    }
}