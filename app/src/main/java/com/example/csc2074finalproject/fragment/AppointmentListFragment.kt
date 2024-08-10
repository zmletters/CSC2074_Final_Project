package com.example.csc2074finalproject.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.csc2074finalproject.R
import com.example.csc2074finalproject.database.AppDatabase
import com.example.csc2074finalproject.databinding.FragmentAppointmentlistBinding
import com.example.csc2074finalproject.databinding.FragmentDoctorListBinding
import com.example.csc2074finalproject.viewModel.AppointmentListAdapter
import com.example.csc2074finalproject.viewModel.AppointmentListViewModel
import com.example.csc2074finalproject.viewModel.AppointmentListViewModelFactory
import com.example.csc2074finalproject.viewModel.DoctorsViewModel
import com.example.csc2074finalproject.viewModel.DoctorsViewModelFactory
import com.example.csc2074finalproject.viewModel.UserViewModel

class AppointmentListFragment: Fragment() {
    private lateinit var binding: FragmentAppointmentlistBinding
    private lateinit var appointmentListViewModel: AppointmentListViewModel
    private lateinit var appointmentListAdapter: AppointmentListAdapter
    private val userViewModel: UserViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setupViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAppointmentlistBinding.inflate(inflater, container, false )

        // Initialize Recycler View and data
        setupRecyclerView()
        observeAppointments()


        binding.btnMakeAppointment.setOnClickListener{
            it.findNavController().navigate(R.id.action_appointmentListFragment_to_makeAppointmentFragment)
        }
        binding.btnGoHome.setOnClickListener {
            it.findNavController().navigate(R.id.action_appointmentListFragment_to_userHomeFragment)
        }

        return binding.root
    }


    private fun setupViewModel() {
        val appointmentDao = AppDatabase.getInstance(requireActivity()).appointmentListDao()
        val factory = AppointmentListViewModelFactory(appointmentDao)
        appointmentListViewModel = ViewModelProvider(this, factory)[AppointmentListViewModel::class.java]
    }

    private fun setupRecyclerView() {

        appointmentListAdapter = AppointmentListAdapter()
        binding.doctorlistRecyclerViewDoctors.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = appointmentListAdapter
        }
    }

    private fun observeAppointments() {
        userViewModel.userLiveDataID.observe(viewLifecycleOwner) {userID ->
            Log.d("AppointmentListFragment", "user ID is $userID")
            userID?.let {
                appointmentListViewModel.getAllAppointments(it).observe(viewLifecycleOwner) {appointments ->
                    appointmentListAdapter.setAppointment(appointments)
                }
            }
        }
    }



}