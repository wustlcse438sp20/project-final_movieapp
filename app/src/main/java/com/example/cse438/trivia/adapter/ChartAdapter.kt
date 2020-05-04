package com.example.cse438.trivia.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.cse438.trivia.AddtoPlaylist
import com.example.cse438.trivia.R
import com.example.cse438.trivia.SelectPlayList
import com.example.cse438.trivia.data.Artist
import com.example.cse438.trivia.data.Track
import com.squareup.picasso.Picasso


//define the binding for the view holder
class ChartViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.question_item, parent, false)) {
    private val trackName: TextView
    private val trackImage: ImageView


    init {
        trackName = itemView.findViewById(R.id.track_name)
        trackImage = itemView.findViewById(R.id.track_image)

    }

    fun bind(track: Track) {
        trackName?.text = track.title
        Picasso.get().load(track.album.cover).into(trackImage)
        trackImage.setOnClickListener() {
            val intent = Intent(trackName.context, AddtoPlaylist::class.java)

            intent.putExtra("trackId", track.id.toString())
            intent.putExtra("album_name", track.album.title)
            intent.putExtra("artist", track.artist.name)
            intent.putExtra("duration", track.duration)
            intent.putExtra("track_position", track.track_position.toString())
            intent.putExtra("rank", track.rank)
            intent.putExtra("release", "2018-2-13")
            intent.putExtra("track_name", track.title)
            intent.putExtra("album_cover", track.album.cover)

            trackName.context.startActivity(intent)
        }

    }
}


//define the adapter for the recycler view
class ChartAdapter(private val list: ArrayList<Track>)
    : RecyclerView.Adapter<ChartViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChartViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ChartViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ChartViewHolder, position: Int) {
        val track: Track = list[position]
        holder.bind(track)
    }

    override fun getItemCount(): Int = list.size
}


