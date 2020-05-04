package com.example.cse438.trivia.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cse438.trivia.AddtoPlaylist
import com.example.cse438.trivia.PlaylistViewModel
import com.example.cse438.trivia.R
import com.example.cse438.trivia.data.PopularMovie
import com.squareup.picasso.Picasso


//define the binding for the view holder
class MovieViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.question_item, parent, false)) {
    private val trackName: TextView
    private val trackImage: ImageView
    lateinit var viewModel: PlaylistViewModel

    init {
        trackName = itemView.findViewById(R.id.track_name)
        trackImage = itemView.findViewById(R.id.track_image)

    }

    fun bind(movie: PopularMovie) {
        trackName?.text = movie.original_title
        val add = "https://image.tmdb.org/t/p/w500" + movie.poster_path
        Picasso.get().load(add).into(trackImage)
        var genre1 =""
        var genre2 =""
        if(movie.genre_ids.size>0) {
            if (movie.genre_ids.get(0) == 28) genre1 = "Action";
            if (movie.genre_ids.get(0) == 12) genre1 = "Adventure";
            if (movie.genre_ids.get(0) == 16) genre1 = "Animation";
            if (movie.genre_ids.get(0) == 35) genre1 = "Comedy";
            if (movie.genre_ids.get(0) == 80) genre1 = "Crime";
            if (movie.genre_ids.get(0) == 99) genre1 = "Documentary";
            if (movie.genre_ids.get(0) == 18) genre1 = "Drama";
            if (movie.genre_ids.get(0) == 10751) genre1 = "Family";
            if (movie.genre_ids.get(0) == 14) genre1 = "Fantasy";
            if (movie.genre_ids.get(0) == 36) genre1 = "History";
            if (movie.genre_ids.get(0) == 27) genre1 = "Horror"
            if (movie.genre_ids.get(0) == 10402) genre1 = "Music"
            if (movie.genre_ids.get(0) == 9048) genre1 = "Mystery"
            if (movie.genre_ids.get(0) == 10749) genre1 = "Romance"
            if (movie.genre_ids.get(0) == 878) genre1 = "Science Fiction"
            if (movie.genre_ids.get(0) == 10770) genre1 = "TV Movie"
            if (movie.genre_ids.get(0) == 53) genre1 = "Thriller"
            if (movie.genre_ids.get(0) == 10752) genre1 = "War"
            if (movie.genre_ids.get(0) == 37) genre1 = "Western"
            if (movie.genre_ids.size > 1) {
                if (movie.genre_ids.get(1) == 28) genre2 = "Action";
                if (movie.genre_ids.get(1) == 12) genre2 = "Adventure";
                if (movie.genre_ids.get(1) == 16) genre2 = "Animation";
                if (movie.genre_ids.get(1) == 35) genre2 = "Comedy";
                if (movie.genre_ids.get(1) == 80) genre2 = "Crime";
                if (movie.genre_ids.get(1) == 99) genre2 = "Documentary";
                if (movie.genre_ids.get(1) == 18) genre2 = "Drama";
                if (movie.genre_ids.get(1) == 10751) genre2 = "Family";
                if (movie.genre_ids.get(1) == 14) genre2 = "Fantasy";
                if (movie.genre_ids.get(1) == 36) genre2 = "History";
                if (movie.genre_ids.get(1) == 27) genre2 = "Horror"
                if (movie.genre_ids.get(1) == 10402) genre2 = "Music"
                if (movie.genre_ids.get(1) == 9048) genre2 = "Mystery"
                if (movie.genre_ids.get(1) == 10749) genre2 = "Romance"
                if (movie.genre_ids.get(1) == 878) genre2 = "Science Fiction"
                if (movie.genre_ids.get(1) == 10770) genre2 = "TV Movie"
                if (movie.genre_ids.get(1) == 53) genre2 = "Thriller"
                if (movie.genre_ids.get(1) == 10752) genre2 = "War"
                if (movie.genre_ids.get(1) == 37) genre2 = "Western"
            }
        }
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
            intent.putExtra("movieVoteAmount", "0")
            if(movie.genre_ids.size>0) intent.putExtra("genre1", genre1)
            if(movie.genre_ids.size>1) intent.putExtra("genre2", genre2)
            // we also need to add genres here it's important!!!
            trackName.context.startActivity(intent)
//        }
        }
    }

}


//define the adapter for the recycler view
class MovieAdapter(private val list: ArrayList<PopularMovie>)
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


