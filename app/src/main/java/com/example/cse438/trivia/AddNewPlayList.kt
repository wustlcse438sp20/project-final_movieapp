package com.example.cse438.trivia

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cse438.trivia.R
import com.example.cse438.trivia.adapter.PlaylistAdapter
import com.example.cse438.trivia.data.Playlist
import kotlinx.android.synthetic.main.activity_addnewplaylist.*
import kotlinx.android.synthetic.main.activity_selectplaylist.*
import kotlinx.android.synthetic.main.fragement_playlist.*
import java.time.LocalDateTime
import java.util.jar.Attributes

class AddNewPlayList() : AppCompatActivity() {

    lateinit var viewModel : PlaylistViewModel
    lateinit var gestureDector : GestureDetectorCompat
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addnewplaylist)
    }

    override fun onStart() {
        super.onStart()
        viewModel = ViewModelProviders.of(this).get(PlaylistViewModel::class.java)
        gestureDector = GestureDetectorCompat(this, MyGestureListener())
        addNewPlaylistButton.setOnClickListener { view ->
            val j = Playlist(
                playlistName.text.toString(),
                newListComment.text.toString(),
                newListRating.text.toString(),
                1.toString(),
                "2020-3-1"
            )

            if(playlistName.text.length != 0 && newListComment.text.length != 0 && newListRating.text.length!=0) {
                viewModel!!.insert(j)

            } else{
                val text = "Must not be null!"
                val duration = Toast.LENGTH_SHORT
                val toast = Toast.makeText(applicationContext, text, duration)
                toast.show()
            }
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