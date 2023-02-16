package com.inlay.concertswatcher.domain.mainList

import com.inlay.concertswatcher.data.models.ConcertsDataNetworkModel
import com.inlay.concertswatcher.domain.mainList.repoes.ConcertsRepository
import retrofit2.Response

class GetConcertsImpl(private val concertsRepository: ConcertsRepository) : GetConcerts {
    override suspend fun getConcerts(
        body: String,
        name: String,
        minDate: String?,
        maxDate: String?,
        page: Int
    ): Response<ConcertsDataNetworkModel> =
        concertsRepository.getConcerts(body, name, minDate, maxDate, page)
}