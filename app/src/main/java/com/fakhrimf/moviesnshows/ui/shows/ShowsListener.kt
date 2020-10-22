package com.fakhrimf.moviesnshows.ui.shows

import com.fakhrimf.moviesnshows.model.ShowModel

interface ShowsListener {
    fun onClick(showModel: ShowModel)
}