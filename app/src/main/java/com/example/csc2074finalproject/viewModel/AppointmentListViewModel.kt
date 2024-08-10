package com.example.csc2074finalproject.viewModel

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.csc2074finalproject.DAO.AppointmentListDao
import com.example.csc2074finalproject.entity.AppointmentList
import kotlinx.coroutines.launch
import java.util.Date

class AppointmentListViewModel(private val dao: AppointmentListDao): ViewModel() {
    fun getAllAppointments(userid: Int): LiveData<List<AppointmentList?>> {
        return dao.readData(userid)
    }

    fun getAppointmentCount(userid: Int): LiveData<Int> {
        return dao.getAppointmentCount(userid)
    }

    fun insertAppointment(userid: Int, doctorname: String, specialty: String, date: String, time:String) {
        val appointment = AppointmentList(
            id = 0,
            userid = userid,
            doctorname = doctorname,
            specialty = specialty,
            date = date,
            time = time
        )

        viewModelScope.launch {
            dao.insertAppointment(appointment)
        }
    }

    fun getLatestAppointmentDate(userid: Int): LiveData<String?> {
        return dao.getLatestAppointmentDate(userid)
    }

}