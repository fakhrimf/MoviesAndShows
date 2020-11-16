package com.fakhrimf.moviesnshows.ui.shows

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
import androidx.recyclerview.widget.LinearLayoutManager
import com.fakhrimf.moviesnshows.R
import com.fakhrimf.moviesnshows.ShowsDetailActivity
import com.fakhrimf.moviesnshows.model.ShowModel
import com.fakhrimf.moviesnshows.utils.SHOW_KEY
import com.fakhrimf.moviesnshows.utils.repository.MovieShowApplication
import kotlinx.android.synthetic.main.show_fragment.*

class ShowsFragment : Fragment(), ShowsListener {

    companion object {
        fun newInstance() = ShowsFragment()
    }

    private val showViewModel: ShowViewModel by viewModels {
        ShowViewModelFactory((requireActivity().application as MovieShowApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.show_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        initDummy()
        setRecycler()
        srl_shows.setOnRefreshListener {
            setRecycler()
        }
    }

    //    For Instrument Testing
    private fun initDummy() {
        val dummy = ArrayList<ShowModel>()
        for (i in 0 until 10) {
            dummy.add(ShowModel("$i", "Shows", "$i", null, null, "$i"))
        }
        rvShows.layoutManager = LinearLayoutManager(context)
        rvShows.adapter = ShowsAdapter(dummy, this)
    }

    private fun setRecycler() {
        showViewModel.getPopularShows()
        srl_shows.isRefreshing = true
        showViewModel.allShows.observe(viewLifecycleOwner, Observer {
            no_data.visibility = View.GONE
            srl_shows.isRefreshing = false
            rvShows.layoutManager = LinearLayoutManager(context)
            rvShows.adapter = ShowsAdapter(it, this)
        })
        val mHandler = Handler(Looper.getMainLooper())
        mHandler.postDelayed({
            no_data.visibility = View.VISIBLE
        }, 10000)
        showViewModel.errorState.observe(viewLifecycleOwner, Observer {
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