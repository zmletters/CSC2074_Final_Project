package com.example.csc2074finalproject.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.csc2074finalproject.databinding.FragmentLoginBinding
import com.example.csc2074finalproject.databinding.FragmentUserhomeBinding
import com.example.csc2074finalproject.viewModel.UserViewModel

class HomeFragment: Fragment() {
    private lateinit var binding: FragmentUserhomeBinding
    private val userViewModel: UserViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentUserhomeBinding.inflate(inflater,container,false)

        userViewModel.userLiveDataName.observe(viewLifecycleOwner) { username ->
            binding.homeUsername.text = username
        }



        return binding.root
    }
}