package com.inlay.concertswatcher.domain.mainList

import com.inlay.concertswatcher.data.ConcertsData
import retrofit2.Response

interface ConcertsRepository {
    suspend fun getConcerts(body: String, name: String, page: Int): Response<ConcertsData>
}