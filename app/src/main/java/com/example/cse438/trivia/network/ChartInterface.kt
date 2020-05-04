package com.example.cse438.trivia.network


import com.example.cse438.trivia.data.TrackPayload
import com.example.cse438.trivia.data.TracksPayload
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ChartInterface {

    //https://api.deezer.com/


    @GET("chart/{chart_id}")
    suspend fun getTracks(@Path("chart_id") chart_id: String) : Response<TracksPayload>

    @GET("track/{track_id}")
    suspend fun getTrack(@Path("track_id") track_id : String) : Response<TrackPayload>

    @GET("search")
    suspend fun getSearchItem(@Query("q") q : String) : Response<TrackPayload>

}
