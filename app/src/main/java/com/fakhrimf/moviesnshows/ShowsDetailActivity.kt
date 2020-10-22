package com.fakhrimf.moviesnshows

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.fakhrimf.moviesnshows.model.ShowModel
import com.fakhrimf.moviesnshows.ui.shows.detail.ShowsDetailFragment
import com.fakhrimf.moviesnshows.utils.SHOW_KEY

class ShowsDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shows_detail_activity)
        val model = intent.getParcelableExtra<ShowModel>(SHOW_KEY)!!
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ShowsDetailFragment.newInstance(model))
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