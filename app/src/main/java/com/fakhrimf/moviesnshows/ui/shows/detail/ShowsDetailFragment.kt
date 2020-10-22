package com.fakhrimf.moviesnshows.ui.shows.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fakhrimf.moviesnshows.R
import com.fakhrimf.moviesnshows.databinding.ShowsDetailFragmentBinding
import com.fakhrimf.moviesnshows.model.ShowModel

class ShowsDetailFragment(private val mModel: ShowModel) : Fragment() {

    companion object {
        fun newInstance(mModel: ShowModel) = ShowsDetailFragment(mModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = ShowsDetailFragmentBinding.inflate(inflater, container, false)
        binding.apply {
            model = mModel
        }
        return binding.root
    }
}