package com.example.cse438.trivia.data

data class SimilarMovie(
    val poster_path : String,
    val original_title : String
)

data class SimilarMovies(
    val results : List<SimilarMovie>
)

