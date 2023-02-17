package com.inlay.concertswatcher.domain.mainList.repoes

import androidx.lifecycle.LiveData
import com.inlay.concertswatcher.data.models.ConcertsDataNetworkModel

abstract class FlowingConcertsDataRepository {
    abstract val concertsDataNetwork: LiveData<ConcertsDataNetworkModel?>

    abstract fun addTempConcerts(concertsDataNetworkModel: ConcertsDataNetworkModel?)
}