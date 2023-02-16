package com.inlay.concertswatcher.data.api

import com.inlay.CONCERTS_API_KEY
import com.inlay.concertswatcher.data.models.ConcertsDataNetworkModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

//https://concerts-artists-events-tracker.p.rapidapi.com/venue/past?name=Hollywood%20bowl&minDate=2021-01-25&maxDate=2022-05-30&page=1

//https://concerts-artists-events-tracker.p.rapidapi.com/artist?name=Ed%20sheeran&page=1

//https://concerts-artists-events-tracker.p.rapidapi.com/location?name=Paris&minDate=2022-05-20&maxDate=2022-05-30&page=1

interface ConcertsApi {
    @Headers(
        "X-RapidAPI-Key: $CONCERTS_API_KEY",
        "X-RapidAPI-Host: concerts-artists-events-tracker.p.rapidapi.com"
    )
    @GET("https://concerts-artists-events-tracker.p.rapidapi.com/{pathData}?")
    suspend fun getConcertsData(
        @Path("pathData") body: String,
        @Query("name") name: String,
        @Query("minDate") minDate: String?,
        @Query("maxDate") maxDate: String?,
        @Query("page") page: Int
    ): Response<ConcertsDataNetworkModel>
}