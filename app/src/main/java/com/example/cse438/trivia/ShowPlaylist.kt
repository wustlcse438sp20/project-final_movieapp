package com.example.cse438.trivia

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cse438.trivia.PlaylistViewModel
import com.example.cse438.trivia.R
import com.example.cse438.trivia.adapter.PlaylistAdapter
import com.example.cse438.trivia.adapter.PlaylistDetailAdapter
import com.example.cse438.trivia.adapter.PlaylistDetailViewHolder
import com.example.cse438.trivia.data.JoinedData
import com.example.cse438.trivia.data.Playlist
import kotlinx.android.synthetic.main.activity_selectplaylist.*
import kotlinx.android.synthetic.main.activity_showplaylist.*
import kotlinx.android.synthetic.main.fragement_home.*
import kotlinx.android.synthetic.main.fragement_playlist.*

//we conduct join operations here
class ShowPlaylist() : AppCompatActivity() {
    //var joinedDataList: ArrayList<JoinedData> = ArrayList<JoinedData>()
    lateinit var viewModel: PlaylistViewModel
    var joinedData: ArrayList<JoinedData> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_showplaylist)
    }

    override fun onStart() {
        super.onStart()
        val playlistId= intent.getStringExtra("playlistId")
        viewModel = ViewModelProviders.of(this).get(PlaylistViewModel::class.java)
        showplaylist_recycler_view.layoutManager = LinearLayoutManager(applicationContext)
        var playlistdetailAdapter = PlaylistDetailAdapter(joinedData, viewModel)
        showplaylist_recycler_view.adapter= playlistdetailAdapter
//        viewModel.updateId(playlistId.toInt())
        viewModel.getData(playlistId.toInt())
        viewModel!!.joinedData.observe(this, Observer {
            this.joinedData.clear()
            this.joinedData.addAll(it)
            playlistdetailAdapter.notifyDataSetChanged()
            PlayListCurrentRating.text= "Current Rating: "+ viewModel.repository.getRating(playlistId.toInt())
        })
        if(RatingOfPlaylist.text!=null){
            AddRating_Button.setOnClickListener(){
                viewModel.updateRating(playlistId.toInt(), RatingOfPlaylist.text.toString().toInt())
            }
        }
    }

}
