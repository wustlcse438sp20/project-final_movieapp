package com.example.cse438.trivia.data

data class PopularMovie(
    val vote_count : Int,
    val poster_path : String,
    val id : Int,
    val original_language : String,
    val popularity : Float,
    val genre_ids : List<Int>,
    val original_title : String,
    val overview : String,
    val release_date : String
)


/*
        "popularity":226.241,
         "vote_count":494,
         "video":false,
         "poster_path":"\/hrfh9uVxcTu21vVxDs40l0CDWGW.jpg",
         "id":414419,
         "adult":false,
         "backdrop_path":null,
         "original_language":"en",
         "original_title":"Kill Bill: The Whole Bloody Affair",
         "genre_ids":[
            28,
            80
         ],
         "title":"Kill Bill: The Whole Bloody Affair",
         "vote_average":8,
         "overview":"Kill Bill: The Whole Bloody Affair is a complete edit of the two-part martial arts action films Kill Bill: Vol. 1 and Kill Bill: Vol. 2. The film was originally scheduled to be released as one part. However, due to the film's over 4 hour running time, it was split into two parts.",
         "release_date":"2011-03-28"
 */
