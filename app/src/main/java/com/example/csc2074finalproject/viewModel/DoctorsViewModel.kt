package com.example.csc2074finalproject.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.csc2074finalproject.DAO.DoctorsDao
import com.example.csc2074finalproject.database.AppDatabase
import com.example.csc2074finalproject.entity.Doctors
import kotlinx.coroutines.launch

class DoctorsViewModel(private val dao: DoctorsDao): ViewModel() {

    val allDoctors: LiveData<List<Doctors?>> = dao.readData()

    fun getDoctors(): LiveData<List<Doctors?>> {
        return dao.readData()
    }

    fun insertDoctor(doctors: Doctors) {
        viewModelScope.launch {
            dao.insertDoctors(doctors)
        }
    }

    fun getDoctorById(id: Int): LiveData<Doctors?> {
        return dao.findDoctorById(id)
    }

}