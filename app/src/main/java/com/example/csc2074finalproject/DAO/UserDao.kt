package com.example.csc2074finalproject.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.csc2074finalproject.entity.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(user: User)

    @Query ("SELECT * FROM user WHERE user.id = :userid")
    fun findUserById(userid: Int): LiveData<User?>

    @Query ("SELECT * FROM user WHERE user.username = :username")
    fun findUserByUsername(username: String): Flow<User?>

    @Query ("SELECT * FROM user WHERE user.id = :userid")
    fun findUserByUsername(userid: Int): LiveData<User?>

    @Query("SELECT * FROM user WHERE user.username=:username LIMIT 1")
    fun isValidUser(username: String): Flow<User?>
}