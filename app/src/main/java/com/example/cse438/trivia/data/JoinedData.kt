package com.example.cse438.trivia.data

import androidx.room.ColumnInfo
import androidx.room.Embedded

data class JoinedData(

//    val name : String,
//    val comment : String,
//    val rating : String,
//    val genre : String,
//    val time : String,
//    val Title : String,
//    val Artist : String,
//    val Duration : String,
//    val TrackId : Int,
//    val AlbumName: String
//    val PlaylistId : Int
//    val AlbumName : String
    @Embedded
    var trackTable: TrackTable? = null,
    @Embedded
    var playlist: Playlist? = null
)
