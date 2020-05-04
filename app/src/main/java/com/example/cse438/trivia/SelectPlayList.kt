package com.example.cse438.trivia

import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cse438.trivia.R
import com.example.cse438.trivia.adapter.PlaylistAdapter
import com.example.cse438.trivia.data.Playlist
import com.example.cse438.trivia.data.Track
import kotlinx.android.synthetic.main.activity_selectplaylist.*
import kotlinx.android.synthetic.main.fragement_playlist.*
import kotlinx.android.synthetic.main.question_item.*

class SelectPlayList() : AppCompatActivity(), Parcelable {
    var  playList: ArrayList<Playlist> = ArrayList<Playlist>()
    lateinit var viewModel : PlaylistViewModel
    lateinit var gestureDector : GestureDetectorCompat
    constructor(parcel: Parcel) : this() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selectplaylist)
    }

    override fun onStart() {
        super.onStart()
        //SONG NAME, ARTIST, TRACK POSITION, DURATION, RELEASE DATE, RANK
        gestureDector = GestureDetectorCompat(this, MyGestureListener())
        val movie_Id: String = intent.getStringExtra("movieId")
        val movie_name: String = intent.getStringExtra("movieTitle")
        val movie_popularity: String = intent.getStringExtra("moviePopularity")
        val movie_language: String = intent.getStringExtra("movieLanguage")
        val movie_posterPath: String = intent.getStringExtra("moviePosterPath")
        val movie_release: String = intent.getStringExtra("movieReleaseDate")
        val movie_vote: String= intent.getStringExtra("movieVote")
        val movie_voteAmount: String= intent.getStringExtra("movieVoteAmount")


        viewModel = ViewModelProviders.of(this).get(PlaylistViewModel::class.java)
        var playlistAdapter = PlaylistAdapter(playList, true, viewModel, movie_Id, movie_name, movie_release, movie_vote, movie_voteAmount, movie_posterPath, movie_posterPath)

        selectplaylist_recycler_view.adapter = playlistAdapter
        selectplaylist_recycler_view.layoutManager = LinearLayoutManager(applicationContext)
        // viewModel.(movie_Id) // fetch the current track
        /*
        attach oberver on playlists. So when new playlists (stored in playlists view model
        has update, it will be automatically updated
        */

        viewModel!!.playLists.observe(this, Observer {
            playList.clear()
            playList.addAll(it)
            playlistAdapter.notifyDataSetChanged()
        })
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SelectPlayList> {
        override fun createFromParcel(parcel: Parcel): SelectPlayList {
            return SelectPlayList(parcel)
        }

        override fun newArray(size: Int): Array<SelectPlayList?> {
            return arrayOfNulls(size)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        gestureDector.onTouchEvent(event)
        return super.onTouchEvent(event)
    }

    private inner class MyGestureListener: GestureDetector.SimpleOnGestureListener() {
        private var swipedistance = 150

        override fun onFling(
            e1: MotionEvent, e2: MotionEvent, velocityX: Float,
            velocityY: Float
        ): Boolean {
            //right swipe
            val xDif = e2.rawX - e1.rawX
            if (e2.x - e1.x > swipedistance || e1.x - e2.x > swipedistance) {
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
            }
            return true
        }
    }
}