package com.fakhrimf.moviesnshows.ui.movies

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fakhrimf.moviesnshows.MoviesDetailActivity
import com.fakhrimf.moviesnshows.R
import com.fakhrimf.moviesnshows.model.MovieModel
import com.fakhrimf.moviesnshows.utils.MOVIE_KEY
import kotlinx.android.synthetic.main.movies_fragment.*

class MoviesFragment : Fragment(), MoviesListener {

    private val moviesViewModel by lazy {
        ViewModelProvider(
            this
        ).get(MoviesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movies_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        initDummy()
        setRecycler()
    }

//    For Instrument Testing
    private fun initDummy() {
        loading.visibility = View.GONE
        val moviesDummy = ArrayList<MovieModel>()
        for (i in 0 until 10) {
            moviesDummy.add(MovieModel("$i", "$i", "$i", null, null, "$i"))
        }
        rvMovie.layoutManager = LinearLayoutManager(context)
        rvMovie.adapter = MoviesAdapter(moviesDummy, this)
    }

    private fun setRecycler() {
        moviesViewModel.getPopularMovies()
        moviesViewModel.moviesList.observe(viewLifecycleOwner, Observer {
            loading.visibility = View.GONE
            rvMovie.layoutManager = LinearLayoutManager(context)
            rvMovie.adapter = MoviesAdapter(it, this)
        })
        val mHandler = Handler(Looper.getMainLooper())
        mHandler.postDelayed({
            no_data.visibility = View.VISIBLE
        }, 10000)
        moviesViewModel.errorState.observe(viewLifecycleOwner, Observer {
            if (it.error) {
                no_data.text = it.errorMessage
                no_data.visibility = View.VISIBLE
            }
        })
    }

    override fun onClick(movieModel: MovieModel) {
        val intent = Intent(context, MoviesDetailActivity::class.java)
        intent.putExtra(MOVIE_KEY, movieModel)
        startActivity(intent)
    }

}