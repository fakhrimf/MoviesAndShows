package com.fakhrimf.moviesnshows

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.fakhrimf.moviesnshows.model.MovieModel
import com.fakhrimf.moviesnshows.ui.movies.detail.MoviesDetailFragment
import com.fakhrimf.moviesnshows.utils.MOVIE_KEY

class MoviesDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movies_detail_activity)
        val model = intent.getParcelableExtra<MovieModel>(MOVIE_KEY)!!
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MoviesDetailFragment.newInstance(model))
                .commitNow()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = model.title
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                super.onBackPressed()
                this.finish()
                true
            }
            else -> true
        }
    }
}