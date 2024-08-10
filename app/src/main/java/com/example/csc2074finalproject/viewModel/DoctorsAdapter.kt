package com.example.csc2074finalproject.viewModel

import android.annotation.SuppressLint
import android.location.GnssAntennaInfo.Listener
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.csc2074finalproject.databinding.ItemDoctorsBinding
import com.example.csc2074finalproject.entity.Doctors

class DoctorsAdapter(private val onItemClick:(Doctors) -> Unit) : RecyclerView.Adapter<DoctorsAdapter.DoctorViewHolder>() {

    private  var doctorList: List<Doctors> = listOf()

    class DoctorViewHolder(val itemBinding: ItemDoctorsBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        return  DoctorViewHolder(
            ItemDoctorsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return doctorList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        val doctor = doctorList[position]
        holder.itemBinding.doctorNameTextView.text = "Dr. " + doctor.name
        holder.itemBinding.doctorSpecialtyTextView.text = "Specialty: " +  doctor.specialty
        holder.itemBinding.doctorQualificationTextView.text = doctor.qualification

        holder.itemView.setOnClickListener {
            onItemClick(doctor)
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun setDoctors(doctors: List<Doctors?>) {
        this.doctorList = doctors as List<Doctors>
        notifyDataSetChanged()
    }


}