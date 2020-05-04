package com.example.cse438.trivia.network


import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.cse438.trivia.data.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class MovieRepository {
    val service = ApiMovieClientClient.makeRetrofitService()


    fun getTracks(resBody: MutableLiveData<Movies>, input : String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getMovies("6982e7d722dd575f1b3974d123747944")

            withContext(Dispatchers.Main) {
                try{
                    if(response.isSuccessful) {
                        resBody.value = response.body()
                    }
                } catch (e: HttpException) {
                    println("Http error")
                }
            }
        }
    }

    fun getSimilar(resBody: MutableLiveData<SimilarMovies>, curr_movie : String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getSimilar(curr_movie,"6982e7d722dd575f1b3974d123747944")

            withContext(Dispatchers.Main) {
                try{
                    if(response.isSuccessful) {
                        resBody.value = response.body()
                    }
                } catch (e: HttpException) {
                    println("Http error")
                }
            }
        }
    }

    fun getReview(resBody: MutableLiveData<MovieReviews>, curr_movie : String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getReviews(curr_movie,"6982e7d722dd575f1b3974d123747944")

            withContext(Dispatchers.Main) {
                try{
                    if(response.isSuccessful) {
                        resBody.value = response.body()
                    }
                } catch (e: HttpException) {
                    println("Http error")
                }
            }
        }
    }

    fun getSearchItem(resBody: MutableLiveData<Movies>, search : String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.searchMovies("6982e7d722dd575f1b3974d123747944", search)

            withContext(Dispatchers.Main) {
                try{
                    if(response.isSuccessful) {
                        resBody.value = response.body()
                    }
                } catch (e: HttpException) {
                    println("Http error")
                }
            }
        }
    }


}