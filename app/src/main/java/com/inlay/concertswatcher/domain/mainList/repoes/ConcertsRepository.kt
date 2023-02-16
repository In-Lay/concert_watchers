package com.inlay.concertswatcher.domain.mainList.repoes

import com.inlay.concertswatcher.data.models.ConcertsDataNetworkModel
import retrofit2.Response

interface ConcertsRepository {
    suspend fun getConcerts(
        body: String,
        name: String,
        minDate: String?,
        maxDate: String?,
        page: Int
    ): Response<ConcertsDataNetworkModel>
}