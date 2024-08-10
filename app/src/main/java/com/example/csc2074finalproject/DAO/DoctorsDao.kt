package com.example.csc2074finalproject.DAO

import androidx.core.app.Person
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.csc2074finalproject.entity.Doctors
import java.util.concurrent.Flow

@Dao
interface DoctorsDao {
    @Insert
    suspend fun insertDoctors(doctor: Doctors)

    @Query("SELECT * FROM doctors ORDER BY name ASC")
    fun readData(): LiveData<List<Doctors?>>

    @Query("SELECT * FROM doctors WHERE doctors.name = :name")
    fun findDoctorByName(name: String): LiveData<Doctors?>

    @Query("SELECT * FROM doctors WHERE id = :id")
    fun findDoctorById(id: Int): LiveData<Doctors?>

}