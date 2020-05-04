package com.example.cse438.trivia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cse438.trivia.R
import com.example.cse438.trivia.data.MovieReview
import com.example.cse438.trivia.data.SimilarMovie
import com.squareup.picasso.Picasso

class MoviewReviewViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.review_layout, parent, false)) {
    private val authorName: TextView
    private val comment: TextView


    init {
        authorName = itemView.findViewById(R.id.author_name)
        comment = itemView.findViewById(R.id.comment)

    }

    fun bind(review: MovieReview) {
        authorName?.text = "Author: " + review.author
        comment?.text = review.content
    }
}

//define the adapter for the recycler view
class ReviewAdapter(private val list: ArrayList<MovieReview>)
    : RecyclerView.Adapter<MoviewReviewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviewReviewViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MoviewReviewViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: MoviewReviewViewHolder, position: Int) {
        val movie: MovieReview = list[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        if(list.size > 2) {
            return 2
        }
        return list.size
    }
}

