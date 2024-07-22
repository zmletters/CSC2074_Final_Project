package com.example.csc2074finalproject.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.csc2074finalproject.viewModel.UserViewModel

class HomeFragment: Fragment() {
    // private lateinit var binding: FragmentHomeBinding
    private val userViewMode: UserViewModel by activityViewModels()
}