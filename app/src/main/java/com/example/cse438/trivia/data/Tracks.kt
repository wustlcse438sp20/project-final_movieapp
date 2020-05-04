package com.example.cse438.trivia.data


data class Tracks(
    val data : List<Track>
)


data class TracksPayload (val tracks : Tracks)