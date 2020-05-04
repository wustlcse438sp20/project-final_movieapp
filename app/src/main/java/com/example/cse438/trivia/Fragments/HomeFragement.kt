package com.example.cse438.trivia.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cse438.trivia.*
import com.example.cse438.trivia.adapter.MovieAdapter
import com.example.cse438.trivia.adapter.SearchAdapter
import com.example.cse438.trivia.data.PopularMovie
import com.example.cse438.trivia.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragement_home.*

class HomeFragement : Fragment() {
    lateinit var viewModel: MovieViewModel

    public var track_list: ArrayList<PopularMovie> = ArrayList()
    var searched_list : ArrayList<PopularMovie> = ArrayList()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragement_home, container, false)
    }
    //     setContentView(R.layout.activity_main)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)



        var chartadapter = MovieAdapter(track_list)
        recyclerView.adapter = chartadapter
        recyclerView.layoutManager = GridLayoutManager(context, 2)

        viewModel!!.movie_list.observe(this, Observer {
            track_list.clear()
            track_list.addAll(it.results)
            chartadapter.notifyDataSetChanged()
        })

        viewModel.getTracks("2") // default chart that's displayed in the mainpage

        var searchadapter = SearchAdapter(searched_list)

        viewModel!!.searched_movie_list.observe(this, Observer {
            recyclerView.adapter = searchadapter //???
            recyclerView.layoutManager = GridLayoutManager(context, 2)
            searched_list.clear()
            searched_list.addAll(it.results)
           searchadapter.notifyDataSetChanged()
        })


        if(search_box.toString()!="") {
           search_button.setOnClickListener {
               val input: String = search_box.toString()
               viewModel.searchMovie(search_box.text.toString()) // get list of tracks by eminem
           }
       } else{
           val text = "Search Must not be null!"
            val duration = Toast.LENGTH_SHORT
           val toast = Toast.makeText(context, text, duration)
           toast.show()
       }

    }
}

