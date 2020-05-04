package com.example.cse438.trivia

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cse438.trivia.data.*
import com.example.cse438.trivia.network.ChartRepository
import com.example.cse438.trivia.network.MovieRepository
import com.example.cse438.trivia.network.PlaylistRepository
import com.example.cse438.trivia.network.PlaylistRoomDatabase
import kotlinx.coroutines.launch

// Class extends AndroidViewModel and requires application as a parameter.
class PlaylistViewModel(application: Application) : AndroidViewModel(application) {

    // The ViewModel maintains a reference to the repository to get data.
    val repository: PlaylistRepository
    val playLists: LiveData<List<Playlist>>
    val trendingplayLists: LiveData<List<Playlist>>
    var trendingData: LiveData<List<JoinedData>>
    val chart_repository: ChartRepository
    val movie_repository: MovieRepository
    var track : MutableLiveData<TrackPayload> = MutableLiveData()
    var joinedData : LiveData<List<JoinedData>> = MutableLiveData()
    var joinedDataMovie : LiveData<List<PopularMovie>> = MutableLiveData()

    init {
        val playlistDao = PlaylistRoomDatabase.getDatabase(application).playlistDao()
        repository = PlaylistRepository(playlistDao)
        playLists = repository.allPlaylists
        chart_repository = ChartRepository()
        trendingData = repository.trendingLists
        trendingplayLists = repository.topPlaylists
        movie_repository = MovieRepository()
    }


    fun getData(playlistId:Int) {
        joinedData = repository.getData(playlistId)

    }



    fun deleteTrackFromPlaylist(trackId : Int) = viewModelScope.launch{
        repository.deleteTrackFromPlaylist(trackId)
    }

    //insert function on view model scope
    fun insert(playlist: Playlist) = viewModelScope.launch{
        repository.insert(playlist)
    }

    //insert function on view model scope
    fun updateRating(playlistId: Int, updatedRating:Int) = viewModelScope.launch{
        val currentRating = repository.getRating(playlistId)
        val currentRatingAmount = repository.getRatingAmount(playlistId)
        repository.updateRatingAmount(playlistId,currentRatingAmount+1)
        repository.updateRating(playlistId, (currentRatingAmount*currentRating+ updatedRating)/(currentRatingAmount+1))
    }

    fun updateMovieRating(movieId: Int, updatedRating:Int) = viewModelScope.launch{
        val currentRating = repository.getMovieRating(movieId)
        val currentRatingAmount = repository.getMovieRatingAmount(movieId)
        repository.updateMovieRatingAmount(movieId,currentRatingAmount+1)
        repository.updateMovieRating(movieId, (currentRatingAmount*currentRating+ updatedRating)/(currentRatingAmount+1))
        val updatedRatingNow = repository.getMovieRatingAmount(movieId)
        repository.updateMovieRatingAmount(movieId,updatedRatingNow+1)
    }


    fun insertTrack(tt : TrackTable) = viewModelScope.launch {
        repository.insertTrack(tt)
    }
/*
    fun getTrack(trackId : String) = viewModelScope.launch {
        movie_repository.getTrack(track, trackId)
    }
*/
}