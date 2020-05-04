package com.example.cse438.trivia.network

import android.graphics.Paint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.cse438.trivia.data.JoinedData
import com.example.cse438.trivia.data.Playlist
import com.example.cse438.trivia.data.TrackTable


//define the DAO for data access to the table
@Dao
interface PlaylistDao {
    @Query("SELECT * FROM playlist_table")
    fun getPlaylists(): LiveData<List<Playlist>>

    @Insert
    fun insert(playlist: Playlist)

    @Query("SELECT COUNT(*) FROM track_table WHERE track_table.TrackId == :trackTableId")
    fun getTrackTableCount(trackTableId:Int): Int

    @Query("UPDATE playlist_table SET rating=:updatedPlaystRating WHERE playlist_table.playlist_id == :playlistId")
    fun updatePlaylistRating(playlistId: Int, updatedPlaystRating: Int)

    @Query("UPDATE playlist_table SET ratingAmount= :updatedRatingAmount WHERE  playlist_table.playlist_id == :playlistId")
    fun updateAmount(playlistId: Int, updatedRatingAmount: Int)

    @Query("SELECT rating FROM playlist_table WHERE playlist_table.playlist_id == :playlistId")
    fun findOutCurrentRating(playlistId: Int): Int

    @Query("SELECT ratingAmount FROM playlist_table WHERE playlist_table.playlist_id == :playlistId")
    fun findOutCurrentRatingAmount(playlistId: Int): Int

    @Query("UPDATE track_table SET Vote=:updatedMovieRating WHERE track_table.TrackId == :movieId")
    fun updateMovieRating(movieId: Int, updatedMovieRating: Int)

    @Query("UPDATE track_table SET VoteAmount= :updatedRatingAmount WHERE track_table.TrackId == :movieId")
    fun updateMovieVoteAmount(movieId: Int, updatedRatingAmount: Int)

    @Query("SELECT Vote FROM track_table WHERE track_table.TrackId == :movieId")
    fun findOutCurrentMovieRating(movieId: Int): Int

    @Query("SELECT VoteAmount FROM track_table WHERE track_table.TrackId == :movieId")
    fun findOutCurrentMovieRatingAmount(movieId: Int): Int


    @Query("DELETE FROM playlist_table")
    fun deleteAll()

    @Insert
    fun insert(atable: TrackTable)

    //missing genre
    @Query("SELECT * from track_table INNER JOIN playlist_table ON track_table.PlaylistId == :playlistId WHERE playlist_table.playlist_id == :playlistId")
    fun join(playlistId : Int) : LiveData<List<JoinedData>>

    //   @Query("SELECT track_table.rank from track_table INNER JOIN playlist_table WHERE track_table.PlaylistId == :playlistId  ")
    //   fun topPlaylist(playlistId : Int) : LiveData<List<JoinedData>>
//
//    @Query("SELECT * from track_table WHERE PlaylistId == :playlistId")
//    fun join(playlistId: Int) : LiveData<List<JoinedData>>

    @Query("DELETE from track_table WHERE TrackId == :trackid")
    fun deleteTrackFromPlaylist(trackid : Int)

    @Query("SELECT * from track_table GROUP BY TrackId ORDER BY Vote DESC")
    fun trending() : LiveData<List<JoinedData>>


    @Query("SELECT * from playlist_table ORDER BY rating DESC")
    fun getTopTracks() : LiveData<List<Playlist>>

    @Query("SELECT Name from playlist_table ORDER BY rating DESC")
    fun getTopTrackNames() : LiveData<List<JoinedData>>

//    @Query("")
//    fun trending() : LiveData<List<P>>

}