package com.fakhrimf.moviesnshows.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fakhrimf.moviesnshows.databinding.LayoutItemMovieBinding
import androidx.databinding.library.baseAdapters.BR
import com.fakhrimf.moviesnshows.model.MovieModel

class MoviesAdapter(
    private val item: ArrayList<MovieModel>,
    private val mListener: MoviesListener
) : RecyclerView.Adapter<MoviesAdapter.Holder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Holder {
        val inflater = LayoutInflater.from(p0.context)
        val binding = LayoutItemMovieBinding.inflate(inflater, p0, false)
        return Holder(binding.apply {
            binding.listener = mListener
        })
    }

    override fun onBindViewHolder(p0: Holder, p1: Int) {
        p0.bind(item[p1])
    }

    override fun getItemCount(): Int {
        return item.size
    }

    inner class Holder(private val binding: LayoutItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MovieModel) {
            binding.setVariable(BR.model, item)
            binding.executePendingBindings()
        }
    }
}