package com.example.cse438.trivia.network

import android.graphics.Paint
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cse438.trivia.data.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class PlaylistRepository(private val playlistDao: PlaylistDao) {

    val allPlaylists: LiveData<List<Playlist>> = playlistDao.getPlaylists()
    val topPlaylists: LiveData<List<Playlist>> = playlistDao.getTopTracks()

    val trendingLists: LiveData<List<JoinedData>> =playlistDao.trending()
//    lateinit var id : myInt

    val service = ApiClient.makeRetrofitService()

    fun getData(id : Int) : LiveData<List<JoinedData>>{
        return playlistDao.join(id)
    }

    fun getTopTrackNames() : LiveData<List<JoinedData>>{
        return playlistDao.getTopTrackNames()
    }

    fun updateRating(id : Int, updatedRating: Int) {
        playlistDao.updatePlaylistRating(id, updatedRating)
    }

    fun updateRatingAmount(id : Int, updatedRatingAmount: Int) {
        playlistDao.updateAmount(id, updatedRatingAmount)
    }

    fun getMovieRating(id : Int) : Int{
        return playlistDao.findOutCurrentMovieRating(id)
    }

    fun getMovieRatingAmount(id : Int) : Int{
        return playlistDao.findOutCurrentMovieRatingAmount(id)
    }

    fun updateMovieRating(id : Int, updatedRating: Int) {
        playlistDao.updateMovieRating(id, updatedRating)
    }

    fun updateMovieRatingAmount(id : Int, updatedRatingAmount: Int) {
        playlistDao.updateMovieVoteAmount(id, updatedRatingAmount)
    }

    fun getRating(id : Int) : Int{
        return playlistDao.findOutCurrentRating(id)
    }

    fun getRatingAmount(id : Int) : Int{
        return playlistDao.findOutCurrentRatingAmount(id)
    }

    fun checkExistenceTrackTable( id: Int): Boolean{
        if(playlistDao.getTrackTableCount(id)>0) {
            return true
        }
        else{
            return false
        }
    }

    fun numberOfTrackTable( id: Int): Int {
        return playlistDao.getTrackTableCount(id)
    }


    fun insert(playList: Playlist) {
        CoroutineScope(Dispatchers.IO).launch {
            playlistDao.insert(playList)
        }
    }



    fun insertTrack(tt : TrackTable) {
        CoroutineScope(Dispatchers.IO).launch {
            playlistDao.insert(tt)
        }
    }

    fun getTrending() {
        CoroutineScope(Dispatchers.IO).launch {
            playlistDao.trending()
        }
    }

    fun deleteTrackFromPlaylist (trackId : Int) {
        CoroutineScope(Dispatchers.IO).launch {
            playlistDao.deleteTrackFromPlaylist(trackId)
        }
    }


    fun getTrack(resBody: MutableLiveData<TrackPayload>, trackId : String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getTrack(trackId)
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