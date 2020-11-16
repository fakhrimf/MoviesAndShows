package com.fakhrimf.moviesnshows.utils.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fakhrimf.moviesnshows.model.MovieModel
import com.fakhrimf.moviesnshows.model.ShowModel

@Database(entities = [MovieModel::class, ShowModel::class], version = 1, exportSchema = false)
abstract class MovieShowDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun showDao(): ShowDao

    companion object {
        // Singleton agar tidak ada multiple instances dari database
        @Volatile
        private var INSTANCE: MovieShowDatabase? = null

        fun getDatabase(context: Context): MovieShowDatabase {
            return INSTANCE ?: synchronized(this::class) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieShowDatabase::class.java,
                    "movie_show_database"
                ).build()
                // Set variable instance menjadi tidak null
                INSTANCE = instance
                // Return instance apabila database null
                instance
            }
        }
    }
}