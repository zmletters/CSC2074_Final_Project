package com.example.csc2074finalproject.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.csc2074finalproject.DAO.DoctorsDao
import com.example.csc2074finalproject.DAO.UserDao
import com.example.csc2074finalproject.entity.User
import com.example.csc2074finalproject.entity.Doctors

@Database(
    entities = [User::class, Doctors::class],
    version = 4,
    exportSchema = true
)


abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun doctorsDao(): DoctorsDao
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance!=null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app.db"
                )
                    .allowMainThreadQueries()
                    .createFromAsset("app.db")
                    .build()
                INSTANCE = instance

                return instance
            }
        }
    }
}