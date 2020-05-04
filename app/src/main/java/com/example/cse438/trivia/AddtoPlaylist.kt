package com.example.cse438.trivia

import android.content.Intent
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cse438.trivia.adapter.ReviewAdapter
import com.example.cse438.trivia.adapter.SimilarAdapter
import com.example.cse438.trivia.data.MovieReview
import com.example.cse438.trivia.data.SimilarMovie
import com.example.cse438.trivia.data.TrackTable
import com.example.cse438.trivia.viewmodel.MovieViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_addtoplaylist.*
import kotlinx.android.synthetic.main.fragement_home.*



class AddtoPlaylist : AppCompatActivity(){

    lateinit var gestureDector : GestureDetectorCompat
    lateinit var scaleGestureDetector: ScaleGestureDetector
    lateinit var  movie_Id: String ;
    lateinit var  movie_name: String ;
    lateinit var  movie_poster_id: String ;
    lateinit var  movie_popularity: String;
    lateinit var  movie_language: String ;
    lateinit var  movie_overview: String ;
    lateinit var  movie_release: String
    lateinit var  movie_voteAmount: String ;
    lateinit var  movie_vote: String ;
    lateinit var  movie_Genre: String ;

    var similarMovies : ArrayList<SimilarMovie> = ArrayList<SimilarMovie>()

    var MovieReviews : ArrayList<MovieReview> = ArrayList<MovieReview>()
    lateinit var viewModel: MovieViewModel
    lateinit var viewModel2: PlaylistViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addtoplaylist)
    }


    override fun onStart() {
        super.onStart()
        viewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        gestureDector = GestureDetectorCompat(this, MyGestureListener())
        val movieRevenue : TextView = findViewById(R.id.movie_revenue)
        val movieImage : ImageView = findViewById(R.id.movie_image)
        val movieName : TextView = findViewById(R.id.movie_name)
        val moviePopularity : TextView = findViewById(R.id.movie_budget)
        val movieLanguage : TextView = findViewById(R.id.movie_revenue)
        val movieOverview : TextView = findViewById(R.id.movie_overview)
        val releaseDate : TextView = findViewById(R.id.release_date)
        val movieGenre : TextView = findViewById(R.id.movie_genre)
        // val trackRank : TextView = findViewById(R.id.track_rank)
        // val trackPosition : TextView = findViewById(R.id.track_position)

        // fetching data from adapter to display in detail page

        movie_Id = intent.getStringExtra("movieId")
        movie_name = intent.getStringExtra("movieTitle")
        movie_poster_id = intent.getStringExtra("moviePosterPath")
        movie_popularity = intent.getStringExtra("moviePopularity")
        movie_language = intent.getStringExtra("movieLanguage")
        movie_overview = intent.getStringExtra("movieOverview")
        movie_release = intent.getStringExtra("movieReleaseDate")
        movie_vote = intent.getStringExtra("movieVote")
        movie_voteAmount = intent.getStringExtra("movieVoteAmount")
        movie_Genre = intent.getStringExtra("genre1")
        if (intent.getStringExtra("genre2") != null) {
            movie_Genre = movie_Genre + " " + intent.getStringExtra("genre2")
        }
        movieName.text = "Movie Name: " + movie_name
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + movie_poster_id).into(movieImage)
        releaseDate.text = "Movie Release Date: " + movie_release
        movieLanguage.text = "Movie Language: " + movie_language
        moviePopularity.text = "Movie Popularity Score: " + movie_popularity
        movieOverview.text = "Movie Overview: " +movie_overview
        movieGenre.text = "Movie Genre: " + movie_Genre

        // get similar movies in movie details

        val chartadapter = SimilarAdapter(similarMovies)
        similar.adapter = chartadapter

        similar.layoutManager = GridLayoutManager(this, 2)

        viewModel!!.similar_movie_list.observe(this, Observer {
            similarMovies.clear()
            similarMovies.addAll(it.results)
            if(similarMovies.size == 0) {
                similar.visibility = View.GONE
            } else {
                title_similar.visibility = View.VISIBLE
            }
            chartadapter.notifyDataSetChanged()
        })

        viewModel.getSimilar(movie_Id) // default chart that's displayed in the mainpage


        //get reviews in movie details

        val review_adapter = ReviewAdapter(MovieReviews)
        reviews.adapter = review_adapter

        reviews.layoutManager = LinearLayoutManager(this)

        viewModel!!.review_list.observe(this, Observer {
            MovieReviews.clear()
            MovieReviews.addAll(it.results)
            if(MovieReviews.size == 0) {
                reviews.visibility = View.GONE
            } else {
               title_review.visibility = View.VISIBLE
            }
            review_adapter.notifyDataSetChanged()
        })
        viewModel.getReviews(movie_Id)

        //below are sending rating to database
        viewModel2 = ViewModelProviders.of(this).get(PlaylistViewModel::class.java)
        if (!viewModel2.repository.checkExistenceTrackTable(movie_Id.toInt())) {
            val tt = TrackTable(
                0, movie_Id.toInt(), movie_name,
                "0",
                "0",
                movie_release, movie_poster_id, movie_poster_id
            )
            viewModel2 = ViewModelProviders.of(this).get(PlaylistViewModel::class.java)
            viewModel2!!.insertTrack(tt)
        }

        AddMoiveNewRating_Button.setOnClickListener {
            if (movie_Id != "" && New_Movie_Rating.text.toString() != "" && New_Movie_Rating.text.toString() != "0") {

                viewModel2.updateMovieRating(
                    movie_Id.toInt(),
                    New_Movie_Rating.text.toString().toInt()
                )

                val currentRating = viewModel2.repository.getMovieRating(movie_Id.toInt())
                Toast.makeText(
                    this,
                    "Your Review has been updated",
                    Toast.LENGTH_LONG
                ).show();

            } else {
                Toast.makeText(
                    this,
                    "Please input value between 1-10",
                    Toast.LENGTH_LONG
                ).show();
            }
            movie_voteAmount =
                viewModel2.repository.getMovieRatingAmount(movie_Id.toInt()).toString()
            movie_vote = viewModel2.repository.getMovieRating(movie_Id.toInt()).toString()

        }

        Add_To_Playlist_Button.setOnClickListener {
            val intent = Intent(applicationContext, SelectPlayList::class.java)
            intent.putExtra("movieId", movie_Id)
            intent.putExtra("movieTitle", movie_name)
            intent.putExtra("moviePosterPath", movie_poster_id)
            intent.putExtra("moviePopularity", movie_popularity)
            intent.putExtra("movieReleaseDate", movie_release)
            intent.putExtra("movieLanguage", movie_language)
            intent.putExtra("movieOverview", movie_overview)
            intent.putExtra("movieVoteAmount", movie_voteAmount)
            intent.putExtra("movieVote", movie_vote)
            /*
            intent.putExtra("track_id", trackId)
            intent.putExtra("album_name", album_name)
            intent.putExtra("artist_name", artist_name)
            intent.putExtra("track_duration", track_length)
            intent.putExtra("track_length", track_length)
            intent.putExtra("track_rank", rank)
            intent.putExtra("release", release)
            intent.putExtra("track_name", track_name)  */
            startActivity(intent)
        }

        Back_Button.setOnClickListener{
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
        }
        // when addtoplaylist is clicked, pass the data to that activity

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        gestureDector.onTouchEvent(event)
        scaleGestureDetector.onTouchEvent(event)
        return super.onTouchEvent(event)
    }

    private inner class MyGestureListener: GestureDetector.SimpleOnGestureListener() {
        private var swipedistance = 150
        override fun onDoubleTap(e: MotionEvent?): Boolean {
            // val name: String = resources.getResourceEntryName(id)
            val intent = Intent(applicationContext, SelectPlayList::class.java)
            intent.putExtra("movieId", movie_Id)
            intent.putExtra("movieTitle", movie_name)
            intent.putExtra("moviePosterPath", movie_poster_id)
            intent.putExtra("moviePopularity", movie_popularity)
            intent.putExtra("movieReleaseDate", movie_release)
            intent.putExtra("movieLanguage", movie_language)
            intent.putExtra("movieOverview", movie_overview)
            intent.putExtra("movieVoteAmount", movie_voteAmount)
            intent.putExtra("movieVote", movie_vote)
            /*
            intent.putExtra("track_id", trackId)
            intent.putExtra("album_name", album_name)
            intent.putExtra("artist_name", artist_name)
            intent.putExtra("track_duration", track_length)
            intent.putExtra("track_length", track_length)
            intent.putExtra("track_rank", rank)
            intent.putExtra("release", release)
            intent.putExtra("track_name", track_name)  */
            startActivity(intent)
            return true
        }

        override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float,
                             velocityY: Float): Boolean{
            //right swipe
            val xDif = e2.rawX - e1.rawX
            if(e2.x - e1.x > swipedistance || e1.x - e2.x > swipedistance ){
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
            }
            return true
        }
    }

}


