package com.example.csc2074finalproject.fragment

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.csc2074finalproject.R
import com.example.csc2074finalproject.databinding.FragmentRegisterBinding
import com.example.csc2074finalproject.entity.User
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

            if (passwordCheck(password, confpassword)) {
                Toast.makeText(requireContext(), "Passwords are not the same.", Toast.LENGTH_LONG)
                    .show()
            } else if (false) {
                Toast.makeText(requireContext(), "Username has been taken", Toast.LENGTH_LONG).show()
            } else if (inputCheck(username,password,confpassword)) {
                // Create user object
                val user = User(0, username, password)

                // Add data to db
                viewModel.insertUserData(user)
                Toast.makeText(requireContext(), "Successfully registered!", Toast.LENGTH_LONG).show()

                // navigate to login page
                it.findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            } else {
                Toast.makeText(requireContext(), "Please ensure fields are entered correctly.", Toast.LENGTH_LONG).show()
            }
        }

        return binding.root
    }

    private fun inputCheck(username: String, password: String, confpass: String): Boolean {
        return !(TextUtils.isEmpty(username) && TextUtils.isEmpty(password) && TextUtils.isEmpty(confpass))
    }
    private fun passwordCheck(password: String, confpass: String) :Boolean {
        return password != confpass
    }


}