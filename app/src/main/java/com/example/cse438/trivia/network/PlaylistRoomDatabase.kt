package com.example.cse438.trivia.network


import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.example.cse438.trivia.data.Playlist
import com.example.cse438.trivia.data.TrackTable


// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = arrayOf(Playlist::class, TrackTable::class), version = 3, exportSchema = false)
public abstract class PlaylistRoomDatabase : RoomDatabase() {

    abstract fun playlistDao(): PlaylistDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: PlaylistRoomDatabase? = null

        fun getDatabase(context: Context): PlaylistRoomDatabase {
            val tempInstance =
                INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PlaylistRoomDatabase::class.java,
                    "playlist_database"
                ).allowMainThreadQueries().fallbackToDestructiveMigration().build()

                INSTANCE = instance
                return instance
            }
        }

    }
}
