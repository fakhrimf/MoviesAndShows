package com.fakhrimf.moviesnshows.ui.main

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fakhrimf.moviesnshows.HomeActivity
import com.fakhrimf.moviesnshows.R
import com.fakhrimf.moviesnshows.utils.API_KEY
import com.fakhrimf.moviesnshows.utils.backgroundFadeInDuration
import com.fakhrimf.moviesnshows.utils.titleFadeInDuration
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val mRunnable = Runnable {
        startActivity(Intent(requireContext(), HomeActivity::class.java))
        requireActivity().finish()
    }
    private val mHandler = Handler(Looper.getMainLooper())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("FIND THIS", "onActivityCreated: $API_KEY")
        splash()
    }

    private fun splash() {
        main.apply {
            alpha = 0f
            visibility = View.VISIBLE
            animate().apply {
                alpha(1f)
                duration = backgroundFadeInDuration
            }
        }
        title_splash.apply {
            alpha = 0f
            translationY = 10000f
            visibility = View.VISIBLE
            animate().apply {
                alpha(1f)
                translationY(0f)
                duration = titleFadeInDuration
                setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        mHandler.postDelayed(mRunnable, titleFadeInDuration)
                    }
                })
            }
        }
    }

    override fun onDestroy() {
        mHandler.removeCallbacks(mRunnable)
        super.onDestroy()
    }

}