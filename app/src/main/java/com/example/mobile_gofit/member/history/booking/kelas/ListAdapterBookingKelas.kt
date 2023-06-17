package com.example.mobile_gofit.member.history.booking.kelas

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_gofit.databinding.ListBookingKelasMemberBinding
import com.example.mobile_gofit.utils.Format

import com.example.mobile_gofit.retrofit.history.member.booking.kelas.ListBookingKelas

class ListAdapterBookingKelas (
    private val listBooking:ArrayList<ListBookingKelas>,
    private val context: Context,
):RecyclerView.Adapter<ListAdapterBookingKelas.KelasViewHolder>() {

    inner class KelasViewHolder(item:ListBookingKelasMemberBinding):RecyclerView.ViewHolder(item.root){
        private val binding = item
        fun bind(data: ListBookingKelas){
            with(binding) {
                txtId.text = data.idBooking
                txtJenis.text = data.jenis
                txtTglDiBooking.text = Format.formatDateTime(data.tanggalDibooking)
                if(data.status != "Hadir"){
                    txtWaktu.text = "-"
                }else{
                    txtWaktu.text = data.waktuPresensi
                }
                if(data.tarif > 1){
                    txtTarif.text = "Rp.${data.tarif}"
                }else{
                    txtTarif.text = "${data.tarif}x"
                }
                txtStatus.text = data.status
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType:
    Int ): KelasViewHolder {
        return KelasViewHolder(ListBookingKelasMemberBinding.inflate(
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