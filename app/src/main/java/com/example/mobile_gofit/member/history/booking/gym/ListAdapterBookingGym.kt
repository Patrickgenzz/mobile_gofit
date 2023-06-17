package com.example.mobile_gofit.member.history.booking.gym

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_gofit.databinding.ListBookingGymMemberBinding
import com.example.mobile_gofit.utils.Format

import com.example.mobile_gofit.retrofit.history.member.booking.gym.ListBookingGym

class ListAdapterBookingGym (
    private val listBooking:ArrayList<ListBookingGym>,
    private val context: Context,
):RecyclerView.Adapter<ListAdapterBookingGym.KelasViewHolder>() {

    inner class KelasViewHolder(item:ListBookingGymMemberBinding):RecyclerView.ViewHolder(item.root){
        private val binding = item
        fun bind(data: ListBookingGym){
            with(binding) {
                txtId.text = data.id
                txtTglDiBooking.text = Format.formatDateTime(data.tanggalDibooking)
                if(data.status != "Hadir"){
                    txtWaktu.text = "-"
                }else{
                    txtWaktu.text = data.waktuPresensi
                }
                txtSlot.text = "Waktu ${data.slot} Jam"
                txtStatus.text = data.status
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType:
    Int ): KelasViewHolder {
        return KelasViewHolder(ListBookingGymMemberBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,false
        ))
    }

    override fun onBindViewHolder(holder: KelasViewHolder,
                                  position: Int) {
        holder.bind(listBooking[position])
    }

    override fun getItemCount(): Int = listBooking.size
}