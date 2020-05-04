package com.example.cse438.trivia.Fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cse438.trivia.adapter.PlaylistAdapter
import com.example.cse438.trivia.data.Playlist
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cse438.trivia.*
import kotlinx.android.synthetic.main.activity_addnewplaylist.*
import kotlinx.android.synthetic.main.activity_addtoplaylist.*
import kotlinx.android.synthetic.main.fragement_home.*
import kotlinx.android.synthetic.main.fragement_playlist.*

class PlaylistFragment : Fragment() {
    var  playList: ArrayList<Playlist> = ArrayList<Playlist>()
    lateinit var viewModel : PlaylistViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragement_playlist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(PlaylistViewModel::class.java)
        //trackId: String,
        //                      title: String, artist: String, duration: String, rank: String, gain: String

        var playlistAdapter = PlaylistAdapter(playList, false, viewModel,  "", "", "",
            "", "", "", "")

        playlist_recycler_view.adapter = playlistAdapter
        playlist_recycler_view.layoutManager = LinearLayoutManager(context)


        viewModel!!.playLists.observe(this, Observer {
            playList.clear()
            playList.addAll(it)
            playlistAdapter.notifyDataSetChanged()
        })
        initializeNewPlaylist.setOnClickListener{
            val intent = Intent(context, AddNewPlayList::class.java)
            startActivity(intent)
        }

    }

}
