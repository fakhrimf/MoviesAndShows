package com.fakhrimf.moviesnshows.utils.repository.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fakhrimf.moviesnshows.model.MovieModel
import com.fakhrimf.moviesnshows.model.ShowModel

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie_table")
    fun getAllMovies(): LiveData<List<MovieModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movieModel: MovieModel)

//    @Query("DELETE FROM movie_table")
//    suspend fun deleteAllMovies()
}

@Dao
interface ShowDao {
    @Query("SELECT * FROM show_table")
    fun getAllShows(): LiveData<List<ShowModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertShows(showModel: ShowModel)

//    @Query("DELETE FROM show_table")
//    suspend fun deleteAllShows()
}