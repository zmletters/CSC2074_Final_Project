package com.example.csc2074finalproject.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.csc2074finalproject.DAO.UserDao
import com.example.csc2074finalproject.entity.User
import kotlinx.coroutines.launch
import java.util.Objects


class UserViewModel(private val dao: UserDao) : ViewModel() {
    val userLiveData: MutableLiveData<User?> = MutableLiveData()
    val userLiveDataName: MutableLiveData<String?> = MutableLiveData()
    val userLiveDataID: MutableLiveData<Int?> = MutableLiveData()

    //val getUserName = userLiveData.value?.username

    fun findCurrentUser(name: String) = viewModelScope.launch {
        dao.findUserByUsername(name).collect {x ->
            if (Objects.isNull(x)) {
                val user = User(0, name, "asdf")
                insertUserData(user)
                userLiveData.value = user
            } else {
                userLiveData.postValue(x)
            }
        }
    }

    fun insertUserData(user: User) {
        viewModelScope.launch {
            Log.d("UserViewModel", "Inserting Data: $user")
            dao.insertUser(user)
            Log.d("UserViewModel", "Data inserted")
            //dao.insertUser(User(0,"qwe","123"))
        }
    }

    fun signInUser(name: String, password: String): Boolean {
        viewModelScope.launch {
            Log.d("UserViewModel", "SignInUser")
            val boo = dao.checkPassword(name, password)
            Log.d("UserViewModel", "Validation: $boo")
            if (boo) {
                userLiveDataName.value = name
                userLiveDataID.value = dao.findUserID(name)
            }
        }
        return dao.checkPassword(name, password)
    }

}