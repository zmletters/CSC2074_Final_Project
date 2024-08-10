package com.example.csc2074finalproject.fragment

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.provider.SyncStateContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.csc2074finalproject.R
import com.example.csc2074finalproject.database.AppDatabase
import com.example.csc2074finalproject.databinding.FragmentDoctorDetailsBinding
import com.example.csc2074finalproject.entity.Doctors
import com.example.csc2074finalproject.viewModel.DoctorsViewModel
import com.example.csc2074finalproject.viewModel.DoctorsViewModelFactory

class DoctorDetailsFragment: Fragment() {
    private lateinit var binding: FragmentDoctorDetailsBinding
    //private lateinit var doctor: Doctors
    private lateinit var doctorsViewModel: DoctorsViewModel
    private val args: DoctorDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDoctorDetailsBinding.inflate(inflater,container,false)
        //argument HERE
        setupViewModel()
        fetchAndDisplayDoctorDetails()

        binding.buttonBack.setOnClickListener {
            it.findNavController().navigate(R.id.action_doctorDetailsFragment_to_doctorListFragment)
        }
        return binding.root
    }

    @SuppressLint("SetTextI18n", "DiscouragedApi")
    private fun displayDoctorDetails(doctor: Doctors) {
        binding.textViewName.text = "Dr. ${doctor.name}"
        binding.textViewSpecialty.text = doctor.specialty
        binding.textViewQualification.text = doctor.qualification
        binding.textViewDetails.text = doctor.details

        val resID: Int = resources.getIdentifier("doctor_2", "Drawable", context?.packageName.toString() )
        Log.d("Doctor Details", "resID: $resID")

        binding.imageView.setImageResource(resID)
    }

    private fun setupViewModel() {
        val doctorsDao = AppDatabase.getInstance(requireActivity()).doctorsDao()
        val factory = DoctorsViewModelFactory(doctorsDao)
        doctorsViewModel = ViewModelProvider(this, factory)[DoctorsViewModel::class.java]
    }

    private fun fetchAndDisplayDoctorDetails() {
        val doctorId = args.doctorArgs
        doctorsViewModel.getDoctorById(doctorId).observe(viewLifecycleOwner) { doctor ->
            if (doctor != null) {
                displayDoctorDetails(doctor)
            }
        }
    }
}