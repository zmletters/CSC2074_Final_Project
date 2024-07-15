package com.example.csc2074finalproject.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.csc2074finalproject.DAO.UserDao
import com.example.csc2074finalproject.entity.User
import kotlinx.coroutines.launch
import java.util.Objects


class UserViewModel(private val dao: UserDao) : ViewModel() {
    private val userLiveData: MutableLiveData<User?> = MutableLiveData()

    fun insertUserData(user: User) {
        viewModelScope.launch {

        }
    }
}