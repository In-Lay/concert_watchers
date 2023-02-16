package com.inlay.concertswatcher.data.repoes

import com.inlay.concertswatcher.data.models.ConcertsDataNetworkModel
import com.inlay.concertswatcher.domain.mainList.api.ConcertsApiService
import com.inlay.concertswatcher.domain.mainList.repoes.ConcertsRepository
import retrofit2.Response

class ConcertsRepoImpl(private val concertsApiService: ConcertsApiService) : ConcertsRepository {
    override suspend fun getConcerts(
        body: String,
        name: String,
        minDate: String?,
        maxDate: String?,
        page: Int
    ): Response<ConcertsDataNetworkModel> =
        concertsApiService.getConcertsData(body, name, minDate, maxDate, page)
}