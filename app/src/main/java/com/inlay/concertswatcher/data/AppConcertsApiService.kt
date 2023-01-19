package com.inlay.concertswatcher.data

import com.inlay.concertswatcher.domain.mainList.ConcertsApiService
import retrofit2.Response

class AppConcertsApiService(private val concertsApi: ConcertsApi) : ConcertsApiService {
    override suspend fun getConcertsData(
        body: String,
        name: String,
        page: Int
    ): Response<ConcertsData> =
        concertsApi.getConcertsData(body, name, page)
}