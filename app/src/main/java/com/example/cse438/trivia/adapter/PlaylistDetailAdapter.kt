package com.example.cse438.trivia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cse438.trivia.PlaylistViewModel
import com.example.cse438.trivia.R
import com.example.cse438.trivia.data.JoinedData
import com.squareup.picasso.Picasso


//define the binding for the view holder
class PlaylistDetailViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.playlist_detail, parent, false)) {
    private val trackName: TextView
    private val trackRating: TextView
    private val trackImage: ImageView
    private val deleteButton : Button



    init {
        trackName = itemView.findViewById(R.id.playlist_detail_track_name)
        trackRating= itemView.findViewById(R.id.playlist_detail_rating)
        trackImage = itemView.findViewById(R.id.playlist_detail_image)
        deleteButton = itemView.findViewById(R.id.Playlist_detail_DeleteButton)
    }

    fun bind(joinedData: JoinedData, vm : PlaylistViewModel) {
        trackName?.text = joinedData.trackTable?.MovieName
        trackRating?.text = "User Rating:" + joinedData.trackTable?.Vote
        val path= joinedData.trackTable?.Revenue
        val add = "https://image.tmdb.org/t/p/w500" + path
        Picasso.get().load(add).into(trackImage)
        // trackArtist?.text = joinedData.trackTable?.Artist
        // trackDuration?.text = "Track Length: " + joinedData.trackTable?.Duration
        //trackRating?.text = "Rating: "  +joinedData.playlist?.rating

        val trackId = joinedData.trackTable?.TrackId
        deleteButton.setOnClickListener{
            vm.deleteTrackFromPlaylist(trackId as Int)
        }
    }
}


//define the adapter for the recycler view
class PlaylistDetailAdapter(private val list : ArrayList<JoinedData>?,  viewModel : PlaylistViewModel)
    : RecyclerView.Adapter<PlaylistDetailViewHolder>() {
    private val listJoinedData : ArrayList<JoinedData>? = list
    private val vm : PlaylistViewModel = viewModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistDetailViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PlaylistDetailViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: PlaylistDetailViewHolder, position: Int) {
        val joinedData: JoinedData = listJoinedData!!.get(position)
        holder.bind(joinedData, vm)
    }

    override fun getItemCount(): Int = listJoinedData!!.size
}

