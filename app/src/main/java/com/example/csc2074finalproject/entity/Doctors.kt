package com.example.csc2074finalproject.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "doctors")
data class Doctors (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo (name = "id")
    var id: Int,

    @ColumnInfo (name = "name")
    var name: String,

    @ColumnInfo (name = "specialty")
    var specialty: String,

    @ColumnInfo (name = "qualification")
    var qualification: String,

    @ColumnInfo (name = "details")
    var details: String,

    @ColumnInfo (name = "imageResId")
    var imageResId: Int,
)