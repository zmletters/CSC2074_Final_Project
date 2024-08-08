package com.example.csc2074finalproject.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.csc2074finalproject.R
import com.example.csc2074finalproject.databinding.FragmentLoginBinding
import com.example.csc2074finalproject.viewModel.UserViewModel

class LoginFragment: Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel: UserViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.loginBtnLogin.setOnClickListener {
            val username = binding.loginUsername.text.toString()
            val password = binding.loginPassword.text.toString()

            // Check Username and password
            Log.d("LoginFrag", "$username $password")
            if (viewModel.signInUser(username, password)) {
                Toast.makeText(context, "Login Successful", Toast.LENGTH_LONG).show()
                it.findNavController().navigate(R.id.action_loginFragment_to_userHomeFragment)
            } else {
                Toast.makeText(context, "Wrong credentials. Please try again!", Toast.LENGTH_LONG).show()
            }
        }

        binding.loginRegisterHereTxt.setOnClickListener {
            it.findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        return binding.root
    }

}