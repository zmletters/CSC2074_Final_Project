package com.example.csc2074finalproject.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.csc2074finalproject.R
import com.example.csc2074finalproject.database.AppDatabase
import com.example.csc2074finalproject.databinding.FragmentLoginBinding
import com.example.csc2074finalproject.databinding.FragmentUserhomeBinding
import com.example.csc2074finalproject.viewModel.AppointmentListViewModel
import com.example.csc2074finalproject.viewModel.AppointmentListViewModelFactory
import com.example.csc2074finalproject.viewModel.UserViewModel

class HomeFragment: Fragment() {
    private lateinit var binding: FragmentUserhomeBinding
    private val userViewModel: UserViewModel by activityViewModels()
    private lateinit var appointmentListViewModel: AppointmentListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentUserhomeBinding.inflate(inflater,container,false)

        // Set up the AppointmentListViewModel
        val appointmentDao = AppDatabase.getInstance(requireActivity()).appointmentListDao()
        val appointmentFactory = AppointmentListViewModelFactory(appointmentDao)
        appointmentListViewModel = ViewModelProvider(this, appointmentFactory).get(
            AppointmentListViewModel::class.java)

        // Observe the user ID and update UI
        userViewModel.userLiveDataID.observe(viewLifecycleOwner) { userID ->
            if (userID != null) {
                appointmentListViewModel.getAppointmentCount(userID).observe(viewLifecycleOwner) { count ->
                    binding.homeAppointmentCount.text = count.toString()
                }

                // Observe the latest appointment date
                appointmentListViewModel.getLatestAppointmentDate(userID).observe(viewLifecycleOwner) { latestDate ->
                    binding.homeAppointmentDateCount.text = latestDate ?: "None"
                }
            }
        }

        userViewModel.userLiveDataName.observe(viewLifecycleOwner) { username ->
            binding.homeUsername.text = username
        }

        binding.homeBtnDoctorList.setOnClickListener {
            it.findNavController().navigate(R.id.action_userHomeFragment_to_doctorListFragment)
        }

        binding.homeBtnAppointmentList.setOnClickListener{
            it.findNavController().navigate(R.id.action_userHomeFragment_to_appointmentListFragment)
        }

        binding.homeBtnMakeAppointment.setOnClickListener{
            it.findNavController().navigate(R.id.action_userHomeFragment_to_makeAppointmentFragment)
        }


        return binding.root
    }
}