package com.example.cse438.trivia.network


import com.example.cse438.trivia.data.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieInterface {

    //https://api.themoviedb.org/3/
    //https://api.themoviedb.org/3/discover/movie?api_key=6982e7d722dd575f1b3974d123747944

    @GET("discover/movie")
    suspend fun getMovies(@Query("api_key") api_key : String ) : Response<Movies>

    @GET("search/movie")
    suspend fun searchMovies(@Query("api_key") q : String, @Query("query") movieName : String) : Response<Movies>

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilar(@Path("movie_id") movie_id : String, @Query("api_key") api_key : String) : Response<SimilarMovies>

    @GET("movie/{movie_id}/reviews")
    suspend fun getReviews(@Path("movie_id") movie_id : String, @Query("api_key") api_key : String) : Response<MovieReviews>

}