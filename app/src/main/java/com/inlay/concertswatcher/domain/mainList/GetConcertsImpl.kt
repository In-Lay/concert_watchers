package com.inlay.concertswatcher.domain.mainList

import com.inlay.concertswatcher.data.ConcertsData
import retrofit2.Response

class GetConcertsImpl(private val concertsRepository: ConcertsRepository) : GetConcerts {
    override suspend fun getConcerts(
        body: String,
        name: String,
        page: Int
    ): Response<ConcertsData> =
        concertsRepository.getConcerts(body, name, page)
}