package com.example.csc2074finalproject.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.csc2074finalproject.entity.AppointmentList

@Dao
interface AppointmentListDao {
    @Insert
    suspend fun insertAppointment(appointment: AppointmentList)

    @Query("SELECT * FROM appointmentlist WHERE userid = :userId ORDER BY date ASC")
    fun readData(userId: Int): LiveData<List<AppointmentList?>>

    @Query("SELECT COUNT(*) FROM appointmentlist WHERE userid = :userId")
    fun getAppointmentCount(userId: Int): LiveData<Int>

    @Query("SELECT date FROM appointmentlist WHERE userid = :userId ORDER BY date ASC LIMIT 1")
    fun getLatestAppointmentDate(userId: Int): LiveData<String?>
}