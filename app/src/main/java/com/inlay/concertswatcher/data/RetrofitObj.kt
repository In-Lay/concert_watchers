package com.inlay.concertswatcher.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitObj {
    private val interceptor: HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

    val retrofit: Retrofit =
        Retrofit.Builder().client(client).addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://concerts-artists-events-tracker.p.rapidapi.com/").build()
}

