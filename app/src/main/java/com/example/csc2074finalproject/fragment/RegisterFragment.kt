package com.example.csc2074finalproject.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.csc2074finalproject.R
import com.example.csc2074finalproject.databinding.FragmentRegisterBinding
import com.example.csc2074finalproject.viewModel.UserViewModel

class RegisterFragment: Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private val viewModel: UserViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentRegisterBinding.inflate(inflater,container,false)
        binding.registerBtnRegister.setOnClickListener {
            val username: String = binding.registerUsername.text.toString()
            val password: String = binding.registerPassword.text.toString()
            val confpassword: String = binding.registerConfirmpassword.text.toString()

            it.findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        return binding.root
    }
}