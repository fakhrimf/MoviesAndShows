package com.fakhrimf.moviesnshows.ui.movies.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fakhrimf.moviesnshows.R
import com.fakhrimf.moviesnshows.databinding.MoviesDetailFragmentBinding
import com.fakhrimf.moviesnshows.model.MovieModel

class MoviesDetailFragment(private val mModel: MovieModel) : Fragment() {

    companion object {
        fun newInstance(movieModel: MovieModel) = MoviesDetailFragment(movieModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = MoviesDetailFragmentBinding.inflate(inflater, container, false)
        binding.apply {
            model = mModel
        }
        return binding.root
    }
}