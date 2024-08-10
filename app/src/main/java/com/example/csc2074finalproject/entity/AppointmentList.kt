package com.example.csc2074finalproject.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "appointmentlist")
data class AppointmentList (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,
    @ColumnInfo(name = "userid")
    var userid:Int,
    @ColumnInfo(name = "doctorname")
    var doctorname:String,
    @ColumnInfo(name = "specialty")
    var specialty:String,
    @ColumnInfo(name = "date")
    var date:String,
    @ColumnInfo(name = "time")
    var time:String,
)