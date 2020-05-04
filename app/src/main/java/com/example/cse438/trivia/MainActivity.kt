package com.example.cse438.trivia

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.cse438.trivia.Fragments.HomeFragement
import com.example.cse438.trivia.Fragments.PlaylistFragment
import com.example.cse438.trivia.Fragments.TopPlaylistFragment
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()
        if (auth.currentUser == null) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        val fragmentAdapter = MyPagerAdapter2(supportFragmentManager)
        viewpager_main.adapter = fragmentAdapter
        tabs_main.setupWithViewPager(viewpager_main)


    }


        class MyPagerAdapter2(fm: FragmentManager) : FragmentPagerAdapter(fm) {

            override fun getCount(): Int {
                return 4
            }

            override fun getItem(position: Int): Fragment {
                return when (position) {
                    0 -> {
                        HomeFragement()
                    }
                    1 -> PlaylistFragment()
                    2 -> TrendingFragment()
                    else -> TopPlaylistFragment()
                }
            }

            override fun getPageTitle(position: Int): CharSequence {
                return when (position) {
                    0 -> "Home"
                    1 -> "Playlist"
                    2 -> "Trending"
                    else -> "TopList"
                }
            }

        }

}


