package com.example.cse438.trivia.data

data class MovieReview(
    val id : String,
    val author : String,
    val content : String
)

data class MovieReviews(
    val results : List<MovieReview>
)