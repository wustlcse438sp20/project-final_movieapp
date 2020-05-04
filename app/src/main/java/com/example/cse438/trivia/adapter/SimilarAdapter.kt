package com.example.cse438.trivia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cse438.trivia.R
import com.example.cse438.trivia.data.PopularMovie
import com.example.cse438.trivia.data.SimilarMovie
import com.squareup.picasso.Picasso

class SimilarMovieViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.question_item, parent, false)) {
    private val trackName: TextView
    private val trackImage: ImageView


    init {
        trackName = itemView.findViewById(R.id.track_name)
        trackImage = itemView.findViewById(R.id.track_image)

    }

    fun bind(movie: SimilarMovie) {
        trackName?.text = movie.original_title
        val add = "https://image.tmdb.org/t/p/w500" + movie.poster_path
        Picasso.get().load(add).into(trackImage)
        trackImage.setOnClickListener() {}
    }
}

//define the adapter for the recycler view
class SimilarAdapter(private val list: ArrayList<SimilarMovie>)
    : RecyclerView.Adapter<SimilarMovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarMovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SimilarMovieViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: SimilarMovieViewHolder, position: Int) {
        val movie: SimilarMovie = list[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        if(list.size > 4) {
            return 4
        }
        return list.size
    }
}

