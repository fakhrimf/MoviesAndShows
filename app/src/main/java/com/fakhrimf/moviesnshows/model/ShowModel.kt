package com.fakhrimf.moviesnshows.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ShowModel(
    val id:String,
    @SerializedName("original_name")
    val title:String,
    val first_air_date:String,
    val backdrop_path:String?,
    val poster_path:String?,
    val overview:String
) : Parcelable