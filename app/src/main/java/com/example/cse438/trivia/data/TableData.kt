package com.example.cse438.trivia.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

//create the sql table
@Entity(tableName = "playlist_table")
data class Playlist(
    @ColumnInfo(name = "Name")
    val name: String,
    @ColumnInfo(name = "comment")
    val comment: String,
    @ColumnInfo(name = "rating")
    val rating: String,
    @ColumnInfo(name = "ratingAmount")
    val genre: String,
    @ColumnInfo(name = "time")
    val time: String
)
{
    @PrimaryKey(autoGenerate = true)
    var playlist_id: Int = 0
}

//(playlist.id, trackId.toInt(), title, artist, duration, album_name)

@Entity(tableName = "track_table")
data class TrackTable(
    @ColumnInfo(name = "PlaylistId")
    val TableId: Int,
    @ColumnInfo(name = "TrackId")
    val TrackId: Int,
    @ColumnInfo(name = "MovieName")
    val MovieName: String,
    @ColumnInfo(name = "Vote")
    val Vote: String,
    @ColumnInfo(name = "VoteAmount")
    val VoteAmount: String,
    @ColumnInfo(name = "ReleaseTime")
    val ReleaseTime: String,
    @ColumnInfo(name = "Language")
    val Revenue: String,
    @ColumnInfo(name = "Popularity")
    val Budget: String

)
{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

@Entity(tableName = "movie_table")
data class MovieRatingTable(
    @ColumnInfo(name = "MovieId")
    val MovieId: Int,
    @ColumnInfo(name = "MovieRating")
    val MovieRating: Float,
    @ColumnInfo(name = "MovieAmount")
    val MovieVoteAmount: Int
)
{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
