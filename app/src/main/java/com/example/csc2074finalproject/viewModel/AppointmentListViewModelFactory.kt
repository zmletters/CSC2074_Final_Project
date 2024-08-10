package com.example.csc2074finalproject.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.csc2074finalproject.DAO.AppointmentListDao


class AppointmentListViewModelFactory (
    private val dao: AppointmentListDao
    ): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AppointmentListViewModel::class.java)) {
            return AppointmentListViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}