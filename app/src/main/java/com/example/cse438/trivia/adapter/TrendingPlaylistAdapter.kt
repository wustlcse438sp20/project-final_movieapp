package com.example.cse438.trivia.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.cse438.trivia.R
import com.example.cse438.trivia.data.Playlist


//create the view holder
class TrendingPlaylistViewHolder(inflater: LayoutInflater, parent: ViewGroup) :

    RecyclerView.ViewHolder(inflater.inflate(R.layout.trendingplaylist_layout, parent, false)) {
    private val name: TextView
    init {
        name = itemView.findViewById(R.id.trendingplaylist_name)
    }

    fun bind(playlist: Playlist) {
        name.text = playlist.name
    }

}

//create the listener for the recycler view
//track_id, track_name, artist_name, track_length)
class TrendingPlaylistAdapter(private val list: ArrayList<Playlist>?)
    : RecyclerView.Adapter<TrendingPlaylistViewHolder>() {
    private var listPlaylists : ArrayList<Playlist>? = list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingPlaylistViewHolder{
        val inflater = LayoutInflater.from(parent.context)
        return TrendingPlaylistViewHolder(inflater, parent)
    }

    //bind the object
    override fun onBindViewHolder(holder: TrendingPlaylistViewHolder, position: Int) {
        val playlist: Playlist = listPlaylists!!.get(position)
        holder.bind(playlist)
    }

    //set the count
    override fun getItemCount(): Int = list!!.size

}


