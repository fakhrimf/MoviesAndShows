package com.fakhrimf.moviesnshows

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.fakhrimf.moviesnshows.ui.movies.MoviesFragment
import com.fakhrimf.moviesnshows.ui.shows.ShowsFragment
import com.fakhrimf.moviesnshows.utils.pages
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.home_fragment.*

class HomeActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_fragment)
        view_pager.adapter = HomePagerAdapter(this)
        TabLayoutMediator(tab_layout, view_pager) { tab, position ->
            when(position) {
                0 -> tab.text = "Movies"
                else -> tab.text = "Shows"
            }
        }.attach()
    }

    private inner class HomePagerAdapter(fragmentActivity: FragmentActivity) :
        FragmentStateAdapter(fragmentActivity) {
        override fun getItemCount(): Int = pages
        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> MoviesFragment()
                else -> ShowsFragment()
            }
        }
    }

}