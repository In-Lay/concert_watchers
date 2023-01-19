package com.inlay.concertswatcher.data.repoes

import com.inlay.concertswatcher.data.ConcertsData
import com.inlay.concertswatcher.domain.mainList.ConcertsApiService
import com.inlay.concertswatcher.domain.mainList.ConcertsRepository
import retrofit2.Response

class ConcertsRepoImpl(private val concertsApiService: ConcertsApiService) : ConcertsRepository {
    override suspend fun getConcerts(
        body: String,
        name: String,
        page: Int
    ): Response<ConcertsData> =
        concertsApiService.getConcertsData(body, name, page)
}