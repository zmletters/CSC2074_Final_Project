package com.example.csc2074finalproject.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "userappointments")
data class UserAppointments (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,
    @ColumnInfo(name = "username")
    var username:String,
    @ColumnInfo(name = "doctor")
    var doctor:String,
    @ColumnInfo(name = "date")
    var date:Long
)