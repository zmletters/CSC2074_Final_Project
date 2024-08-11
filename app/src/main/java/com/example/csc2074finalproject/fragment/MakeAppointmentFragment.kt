package com.example.csc2074finalproject.fragment

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.csc2074finalproject.R
import com.example.csc2074finalproject.database.AppDatabase
import com.example.csc2074finalproject.databinding.FragmentMakeappointmentBinding
import com.example.csc2074finalproject.viewModel.AppointmentListViewModel
import com.example.csc2074finalproject.viewModel.AppointmentListViewModelFactory
import com.example.csc2074finalproject.viewModel.DoctorsViewModel
import com.example.csc2074finalproject.viewModel.DoctorsViewModelFactory
import com.example.csc2074finalproject.viewModel.UserViewModel
import java.util.*

class MakeAppointmentFragment: Fragment() {
    private lateinit var binding: FragmentMakeappointmentBinding
    private lateinit var doctorsViewModel: DoctorsViewModel
    private lateinit var appointmentListViewModel: AppointmentListViewModel
    private val userViewModel: UserViewModel by activityViewModels()
    private val calendar = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMakeappointmentBinding.inflate(inflater, container, false)

        setupViewModel()
        setupDoctorSpinner()
        setupDatePicker()
        setupTimePicker()


        binding.submitButton.setOnClickListener {
            // Handle form submission
            var doctorName = binding.doctorSpinner.selectedItem.toString()
            val date = binding.chooseDateButton.text.toString()
            val time = binding.chooseTimeButton.text.toString()

            // Remove the "Dr." prefix if present
            if (doctorName.startsWith("Dr. ")) {
                doctorName = doctorName.removePrefix("Dr. ")
            }

            userViewModel.userLiveDataID.observe(viewLifecycleOwner) {userID ->
                // Do something with the selected values
                if (userID == null) {
                    Log.d("MakeAppointmentFragment", "User ID is null")
                    return@observe
                }
                doctorsViewModel.getDoctors().observe(viewLifecycleOwner) { doctors ->
                    val doctor = doctors.find { it?.name == doctorName }
                    val specialty = doctor?.specialty ?: "Unknown"

                    // Insert the appointment into the database
                    appointmentListViewModel.insertAppointment(userID, doctorName, specialty, date, time)
                    Log.d("6783", "$doctor & $specialty & $userID")
                    // Navigate to the next fragment or show a confirmation message
                    findNavController().navigate(R.id.action_makeAppointmentFragment_to_appointmentListFragment)
                }
            }
        }

        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_makeAppointmentFragment_to_appointmentListFragment)
        }

        return binding.root
    }

    private fun setupViewModel() {
        val doctorDao = AppDatabase.getInstance(requireActivity()).doctorsDao()
        val appointmentDao = AppDatabase.getInstance(requireActivity()).appointmentListDao()
        val appointmentFactory = AppointmentListViewModelFactory(appointmentDao)
        appointmentListViewModel = ViewModelProvider(this, appointmentFactory).get(AppointmentListViewModel::class.java)
        val factory = DoctorsViewModelFactory(doctorDao)
        doctorsViewModel = ViewModelProvider(this, factory).get(DoctorsViewModel::class.java)
    }

    private fun setupDoctorSpinner() {
        doctorsViewModel.getDoctors().observe(viewLifecycleOwner) { doctors ->
            //val doctorNames = doctors.map { it?.name }
            // Ensure doctors list is not null and contains elements
            val doctorNames = doctors
                .mapNotNull { it?.name } // Get non-null doctor names
                .map { "Dr. $it" } // Add "Dr." prefix to each name

            val adapter = ArrayAdapter(requireContext(), R.layout.spinner_item, doctorNames)
            binding.doctorSpinner.adapter = adapter
        }
    }

    private fun setupDatePicker() {
        binding.chooseDateButton.setOnClickListener {
            DatePickerDialog(requireContext(), { _, year, month, dayOfMonth ->
                val date = "$year/${month + 1}/$dayOfMonth"
                binding.chooseDateButton.text = date
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }
    }

    private fun setupTimePicker() {
        binding.chooseTimeButton.setOnClickListener {
            TimePickerDialog(requireContext(), { _, hourOfDay, minute ->
                val time = String.format("%02d:%02d", hourOfDay, minute)
                binding.chooseTimeButton.text = time
            }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show()
        }
    }

    private fun setupSubmitButton() {
        binding.submitButton.setOnClickListener {
            // Handle form submission
            val doctorName = binding.doctorSpinner.selectedItem.toString()
            val date = binding.chooseDateButton.text.toString()
            val time = binding.chooseTimeButton.text.toString()

            userViewModel.userLiveDataID.observe(viewLifecycleOwner) {userID ->
                // Do something with the selected values
                if (userID == null) {
                    Log.d("MakeAppointmentFragment", "User ID is null")
                    return@observe
                }
                doctorsViewModel.getDoctors().observe(viewLifecycleOwner) { doctors ->
                    val doctor = doctors.find { it?.name == doctorName }
                    val specialty = doctor?.specialty ?: "Unknown"

                    // Insert the appointment into the database
                    appointmentListViewModel.insertAppointment(userID, doctorName, specialty, date, time)
                    Log.d("6783", "$doctor & $specialty & $userID")
                    // Navigate to the next fragment or show a confirmation message
                    findNavController().navigate(R.id.action_makeAppointmentFragment_to_appointmentListFragment)
                }
            }


        }
    }
}