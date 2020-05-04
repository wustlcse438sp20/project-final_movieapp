package com.example.cse438.trivia

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cse438.trivia.R
import com.example.cse438.trivia.adapter.PlaylistAdapter
import com.example.cse438.trivia.adapter.PlaylistDetailAdapter
import com.example.cse438.trivia.adapter.PlaylistDetailViewHolder
import com.example.cse438.trivia.data.JoinedData
import com.example.cse438.trivia.data.Playlist
import com.example.cse438.trivia.data.TrackTable
import kotlinx.android.synthetic.main.activity_selectplaylist.*
import kotlinx.android.synthetic.main.activity_showplaylist.*
import kotlinx.android.synthetic.main.fragement_home.*
import kotlinx.android.synthetic.main.fragement_playlist.*
import kotlinx.android.synthetic.main.fragement_trending.*

//we conduct join operations here
class TrendingFragment() : Fragment() {
    lateinit var viewModel: PlaylistViewModel
    var joinedData: ArrayList<JoinedData> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragement_trending, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PlaylistViewModel::class.java)
        trending_recycler_view.layoutManager = LinearLayoutManager(context)
        var playlistdetailAdapter = PlaylistDetailAdapter(joinedData, viewModel)
        trending_recycler_view.adapter= playlistdetailAdapter
        viewModel!!.trendingData.observe(this, Observer {
            joinedData.clear()
            joinedData.addAll(it)
            playlistdetailAdapter.notifyDataSetChanged()
        })

    }
}
