package com.fakhrimf.moviesnshows.ui.shows

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
import com.fakhrimf.moviesnshows.R
import com.fakhrimf.moviesnshows.ShowsDetailActivity
import com.fakhrimf.moviesnshows.model.ShowModel
import com.fakhrimf.moviesnshows.utils.SHOW_KEY
import kotlinx.android.synthetic.main.show_fragment.*

class ShowsFragment : Fragment(), ShowsListener {

    companion object {
        fun newInstance() = ShowsFragment()
    }

    private val vm by lazy {
        ViewModelProvider(
            this
        ).get(ShowViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.show_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setRecycler()
    }

    private fun setRecycler() {
        vm.getPopularShows()
        vm.showsList.observe(viewLifecycleOwner, Observer {
            loading.visibility = View.GONE
            no_data.visibility = View.GONE
            rvShows.layoutManager = LinearLayoutManager(context)
            rvShows.adapter = ShowsAdapter(it, this)
        })
        val mHandler = Handler(Looper.getMainLooper())
        mHandler.postDelayed({
            no_data.visibility = View.VISIBLE
        }, 10000)
        vm.errorState.observe(viewLifecycleOwner, Observer {
            if (it.error) {
                no_data.text = it.errorMessage
                no_data.visibility = View.VISIBLE
            }
        })
    }

    override fun onClick(showModel: ShowModel) {
        val intent = Intent(context, ShowsDetailActivity::class.java)
        intent.putExtra(SHOW_KEY, showModel)
        startActivity(intent)
    }

}