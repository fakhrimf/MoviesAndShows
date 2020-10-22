package com.fakhrimf.moviesnshows.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieModel(
    val id: String,
    val title: String,
    val release_date: String,
    val backdrop_path: String,
    val poster_path: String,
    val overview: String
) : Parcelable