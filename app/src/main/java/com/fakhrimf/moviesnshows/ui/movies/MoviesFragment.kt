package com.fakhrimf.moviesnshows.ui.movies

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fakhrimf.moviesnshows.MoviesDetailActivity
import com.fakhrimf.moviesnshows.R
import com.fakhrimf.moviesnshows.model.MovieModel
import com.fakhrimf.moviesnshows.utils.MOVIE_KEY
import com.fakhrimf.moviesnshows.utils.repository.MovieShowApplication
import kotlinx.android.synthetic.main.movies_fragment.*

class MoviesFragment : Fragment(), MoviesListener {

    private val moviesViewModel: MoviesViewModel by viewModels {
        MoviesViewModelFactory((requireActivity().application as MovieShowApplication).repository)
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
        srl_movies.setOnRefreshListener {
            setRecycler()
        }
    }

//    For Instrument Testing
    private fun initDummy() {
        val moviesDummy = ArrayList<MovieModel>()
        for (i in 0 until 10) {
            moviesDummy.add(MovieModel("$i", "$i", "$i", null, null, "$i"))
        }
        rvMovie.layoutManager = LinearLayoutManager(context)
        rvMovie.adapter = MoviesAdapter(moviesDummy, this)
    }

    private fun setRecycler() {
        srl_movies.isRefreshing = true
        moviesViewModel.getPopularMovies()
        moviesViewModel.allMovies.observe(viewLifecycleOwner, Observer {
            srl_movies.isRefreshing = false
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