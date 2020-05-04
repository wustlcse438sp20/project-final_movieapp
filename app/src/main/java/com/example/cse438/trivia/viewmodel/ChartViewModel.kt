package com.example.cse438.trivia.viewmodel

import android.app.Application
import android.widget.EditText
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.cse438.trivia.data.TrackPayload
import com.example.cse438.trivia.data.TracksPayload
import com.example.cse438.trivia.network.ChartRepository

class ChartViewModel(application: Application): AndroidViewModel(application) {
    public var track_list: MutableLiveData<TracksPayload> = MutableLiveData()
    public var chart_repository: ChartRepository = ChartRepository()
    public var searched : MutableLiveData<TrackPayload> = MutableLiveData()
    public var trending_list: MutableLiveData<TracksPayload> = MutableLiveData()

    fun getTracks(input : String) {
        chart_repository.getTracks(track_list, input)
    }

    fun getArtist(input : String) {
        chart_repository.getSearchItem(searched, input)
    }


//    fun getQuestionsBySearch(category: String, amount: String) {
//        triviaRepository.getQuestionsBySearch(questionList, category, amount)
//    }
}