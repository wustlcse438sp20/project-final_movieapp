package com.example.cse438.trivia.network

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiMovieClientClient {
    const val BASE_URL = "https://api.themoviedb.org/3/"

    fun makeRetrofitService(): MovieInterface {
        //make api calls from client side
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create(MovieInterface::class.java)
    }
}