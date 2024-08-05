package com.example.csc2074finalproject.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.csc2074finalproject.DAO.UserDao
import com.example.csc2074finalproject.entity.User

@Database(
    entities = [User::class],
    version = 1,
    exportSchema = true
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app"
                    )
                        .createFromAsset("app.db")
                        .build()
                }
                return instance
            }
        }
    }
}