package com.example.csc2074finalproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.csc2074finalproject.database.AppDatabase
import com.example.csc2074finalproject.databinding.ActivityMainBinding
import com.example.csc2074finalproject.viewModel.UserViewModel
import com.example.csc2074finalproject.viewModel.UserViewModelFactory


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val dao = AppDatabase.getInstance(this).userDao()
        val factory = UserViewModelFactory(dao)
        viewModel = ViewModelProvider(this, factory) [UserViewModel::class.java]
    }
}
