package com.example.csc2074finalproject.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.csc2074finalproject.DAO.DoctorsDao

class DoctorsViewModelFactory (
    private val dao: DoctorsDao
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DoctorsViewModel::class.java)) {
            return DoctorsViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}