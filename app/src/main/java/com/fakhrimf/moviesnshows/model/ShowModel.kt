package com.fakhrimf.moviesnshows.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "show_table")
data class ShowModel(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: String,
    @SerializedName("original_name")
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "first_air_date") val first_air_date: String,
    @ColumnInfo(name = "backdrop_path") val backdrop_path: String?,
    @ColumnInfo(name = "poster_path") val poster_path: String?,
    @ColumnInfo(name = "overview") val overview: String
) : Parcelable