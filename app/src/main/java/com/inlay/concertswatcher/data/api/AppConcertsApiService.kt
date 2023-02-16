package com.inlay.concertswatcher.data.api

import com.inlay.concertswatcher.data.models.ConcertsDataNetworkModel
import com.inlay.concertswatcher.domain.mainList.api.ConcertsApiService
import retrofit2.Response

class AppConcertsApiService(private val concertsApi: ConcertsApi) : ConcertsApiService {
    override suspend fun getConcertsData(
        body: String,
        name: String,
        minDate: String?,
        maxDate: String?,
        page: Int
    ): Response<ConcertsDataNetworkModel> =
        concertsApi.getConcertsData(body, name, minDate, maxDate, page)
}