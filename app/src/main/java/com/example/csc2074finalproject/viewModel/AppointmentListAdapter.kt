package com.example.csc2074finalproject.viewModel

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.csc2074finalproject.databinding.ItemAppointmentlistBinding
import com.example.csc2074finalproject.entity.AppointmentList

class AppointmentListAdapter(): RecyclerView.Adapter<AppointmentListAdapter.AppointmentViewHolder>() {
    private var appointmentList: List<AppointmentList> = listOf()

    class AppointmentViewHolder(val itemBinding: ItemAppointmentlistBinding):
            RecyclerView.ViewHolder(itemBinding.root){

            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentViewHolder {
        return AppointmentViewHolder(
            ItemAppointmentlistBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return appointmentList.size
    }

    override fun onBindViewHolder(holder: AppointmentViewHolder, position: Int) {
        val appt = appointmentList[position]

        holder.itemBinding.doctorNameTextView.text = "Dr. " + appt.doctorname
        holder.itemBinding.specialtyTextView.text = appt.specialty
        holder.itemBinding.dateTextView.text = appt.date
        holder.itemBinding.timeTextView.text = appt.time
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setAppointment(appt: List<AppointmentList?>) {
        this.appointmentList = appt as List<AppointmentList>
        notifyDataSetChanged()
    }
}