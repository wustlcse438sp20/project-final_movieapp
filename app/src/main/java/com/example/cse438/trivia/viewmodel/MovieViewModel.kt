package com.example.cse438.trivia.viewmodel

import android.app.Application
import android.widget.EditText
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.cse438.trivia.data.MovieReviews
import com.example.cse438.trivia.data.Movies
import com.example.cse438.trivia.data.SimilarMovies
import com.example.cse438.trivia.network.MovieRepository

class MovieViewModel(application: Application): AndroidViewModel(application) {
        public var movie_list: MutableLiveData<Movies> = MutableLiveData()
    public var searched_movie_list: MutableLiveData<Movies> = MutableLiveData()
     var similar_movie_list: MutableLiveData<SimilarMovies> = MutableLiveData()
    var review_list: MutableLiveData<MovieReviews> = MutableLiveData()

    public var chart_repository: MovieRepository = MovieRepository()
//    public var searched: MutableLiveData<PopularMoviePayload> = MutableLiveData()
//    public var trending_list: MutableLiveData<TracksPayload> = MutableLiveData()

    fun getTracks(input: String) {
        chart_repository.getTracks(movie_list, input)
    }
//
    fun searchMovie(input: String) {
        chart_repository.getSearchItem(searched_movie_list, input)
    }

    fun getSimilar(input: String) {
        chart_repository.getSimilar(similar_movie_list, input)
    }

    fun getReviews(input : String) {
        chart_repository.getReview(review_list, input)
    }
}