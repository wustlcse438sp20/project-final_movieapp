package com.example.cse438.trivia.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cse438.trivia.AddtoPlaylist
import com.example.cse438.trivia.R
import com.example.cse438.trivia.data.PopularMovie
import com.example.cse438.trivia.data.Track
import com.squareup.picasso.Picasso

class SearchViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.question_item, parent, false)) {
    private val trackName: TextView
    private val trackImage: ImageView


    init {
        trackName = itemView.findViewById(R.id.track_name)
        trackImage = itemView.findViewById(R.id.track_image)

    }
    fun bind(movie: PopularMovie) {
        trackName?.text = movie.original_title

        val add = "https://image.tmdb.org/t/p/w500" + movie.poster_path
        Picasso.get().load(add).into(trackImage)
        trackImage.setOnClickListener() {
            val intent = Intent(trackName.context, AddtoPlaylist::class.java)
            intent.putExtra("movieId", movie.id.toString())
            intent.putExtra("movieTitle", movie.original_title)
            intent.putExtra("moviePosterPath", movie.poster_path)
            intent.putExtra("moviePopularity", movie.popularity.toString())
            intent.putExtra("movieReleaseDate", movie.release_date)
            intent.putExtra("movieLanguage", movie.original_language)
            intent.putExtra("movieOverview", movie.overview)
            intent.putExtra("movieVote", movie.vote_count.toString())
            // we also need to add genres here it's important!!!
            trackName.context.startActivity(intent)
//        }
        }
    }

}


//define the adapter for the recycler view
class SearchAdapter(private val list: ArrayList<PopularMovie>)
    : RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie: PopularMovie = list[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = list.size
}

