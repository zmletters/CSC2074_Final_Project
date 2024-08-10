package com.example.csc2074finalproject.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.csc2074finalproject.R
import com.example.csc2074finalproject.database.AppDatabase
import com.example.csc2074finalproject.databinding.FragmentDoctorListBinding
import com.example.csc2074finalproject.viewModel.DoctorsAdapter
import com.example.csc2074finalproject.viewModel.DoctorsViewModel
import com.example.csc2074finalproject.viewModel.DoctorsViewModelFactory

class DoctorListFragment: Fragment() {
    private lateinit var binding: FragmentDoctorListBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var doctorsAdapter: DoctorsAdapter
    private lateinit var doctorsViewModel: DoctorsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        setupViewModel()
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDoctorListBinding.inflate(inflater,container,false)

        // Initialize Recycler View and data
        setupRecyclerView()

        binding.homeButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_doctorListFragment_to_userHomeFragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        doctorsViewModel.getDoctors().observe(viewLifecycleOwner) { doctors ->
            doctors?.let {
                doctorsAdapter.setDoctors(it)
            }
        }
    }

    private fun setupViewModel() {

        val doctorsDao = AppDatabase.getInstance(requireActivity()).doctorsDao()
        val factory = DoctorsViewModelFactory(doctorsDao)
        doctorsViewModel = ViewModelProvider(this, factory)[DoctorsViewModel::class.java]
    }


    private fun setupRecyclerView() {
        doctorsAdapter = DoctorsAdapter {doctors ->
            val action = DoctorListFragmentDirections.actionDoctorListFragmentToDoctorDetailsFragment(doctors.id)
            findNavController().navigate(action)
        }
        binding.doctorlistRecyclerViewDoctors.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = doctorsAdapter
        }


    }
}