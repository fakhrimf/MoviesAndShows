package com.fakhrimf.moviesnshows.ui.shows

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.fakhrimf.moviesnshows.databinding.LayoutItemShowBinding
import com.fakhrimf.moviesnshows.model.ShowModel

class ShowsAdapter(
    private val item: List<ShowModel>,
    private val mListener: ShowsListener
) : RecyclerView.Adapter<ShowsAdapter.Holder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Holder {
        val inflater = LayoutInflater.from(p0.context)
        val binding = LayoutItemShowBinding.inflate(inflater, p0, false)
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

    inner class Holder(private val binding: LayoutItemShowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ShowModel) {
            binding.setVariable(BR.model, item)
            binding.executePendingBindings()
        }
    }
}