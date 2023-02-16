package com.inlay.concertswatcher.domain.mainList.api

import com.inlay.concertswatcher.data.models.ConcertsDataNetworkModel
import retrofit2.Response

interface ConcertsApiService {
    suspend fun getConcertsData(
        body: String,
        name: String,
        minDate: String?,
        maxDate: String?,
        page: Int
    ): Response<ConcertsDataNetworkModel>
}