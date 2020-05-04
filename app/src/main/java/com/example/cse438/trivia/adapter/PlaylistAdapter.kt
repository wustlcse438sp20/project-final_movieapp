package com.example.cse438.trivia.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.cse438.trivia.PlaylistViewModel
import com.example.cse438.trivia.R
import com.example.cse438.trivia.ShowPlaylist
import com.example.cse438.trivia.data.Playlist
import com.example.cse438.trivia.data.TrackTable


//create the view holder
class PlaylistViewHolder(inflater: LayoutInflater, parent: ViewGroup, check : Boolean,
                         mViewModel : PlaylistViewModel
) :

    RecyclerView.ViewHolder(inflater.inflate(R.layout.playlist_layout, parent, false)) {
    private val name: TextView
    private val comment : TextView
    val checkPass: Boolean

    lateinit var viewModel : PlaylistViewModel


    init {
        name = itemView.findViewById(R.id.playlist_name)
        comment = itemView.findViewById(R.id.playlist_comment)
        checkPass=check
        viewModel = mViewModel
    }

    fun bind(playlist: Playlist, movieId: String,
             movieName: String, releaseTime: String, vote: String, voteAmount: String, language: String, popularity: String) {
        name.text = playlist.name
        comment.text = "Playlist Comment: " + playlist.comment

        if(!checkPass){
            name.setOnClickListener{
                val intent = Intent(name.context, ShowPlaylist::class.java)
                intent.putExtra("playlistId", playlist.playlist_id.toString())
                name.context.startActivity(intent)
            }}
        else{
            name.setOnClickListener { view ->
                val t = viewModel.track
                val tt = TrackTable(playlist.playlist_id, movieId.toInt(), movieName, vote, voteAmount, releaseTime, language, popularity)
                viewModel!!.insertTrack(tt)
                val text = "This movie is added to playlist"
                val mToast = Toast.makeText(name.context, text, Toast.LENGTH_SHORT)
                mToast.show()
            }
        }
    }

}

//create the listener for the recycler view
//track_id, track_name, artist_name, track_length)
class PlaylistAdapter(private val list: ArrayList<Playlist>? , pass : Boolean, nViewModel: PlaylistViewModel, movieId: String,
                      movieName: String, releaseTime: String, vote: String, voteAmount: String, language: String, popularity: String)
    : RecyclerView.Adapter<PlaylistViewHolder>() {
    private var listPlaylists : ArrayList<Playlist>? = list
    private val check : Boolean = pass
    private val viewModel: PlaylistViewModel =nViewModel

    private val tid : String = movieId
    private val movieName : String = movieName
    private val releaseTime : String = releaseTime
    private val vote : String = vote
    private val voteAmount : String = voteAmount
    private val language : String = language
    private val popularity : String = popularity


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PlaylistViewHolder(inflater, parent, check, viewModel)
    }

    //bind the object
    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        val playlist: Playlist = listPlaylists!!.get(position)
        holder.bind(playlist, tid , movieName, releaseTime, vote, voteAmount, language, popularity)
    }

    //set the count
    override fun getItemCount(): Int = list!!.size

}


