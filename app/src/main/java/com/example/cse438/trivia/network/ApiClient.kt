package com.example.cse438.trivia.network

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiClient {
    const val BASE_URL = "https://api.deezer.com/"

    fun makeRetrofitService(): ChartInterface {
        //make api calls from client side
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create(ChartInterface::class.java)

    }
}