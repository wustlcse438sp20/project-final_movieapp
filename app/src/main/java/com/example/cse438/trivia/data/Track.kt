package com.example.cse438.trivia.data

import java.util.*

data class Track(
    val id : Int,
    val title : String,
    val duration : String,
    val track_position : Int,
    val rank : String,
    val release_date : String,
    val artist : Artist,
    val album : Album

)

data class TrackPayload (val data : List<Track>)