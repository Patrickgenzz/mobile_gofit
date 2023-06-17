package com.example.mobile_gofit.instruktur.history

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_gofit.databinding.ListPresensiKelasInstrukturBinding
import com.example.mobile_gofit.utils.Format
import com.example.mobile_gofit.retrofit.history.instruktur.ListPresensiInstruktur

class ListPresensiAdapterInstruktur (
    private val listPresensi:ArrayList<ListPresensiInstruktur>,
    private val context: Context,
):RecyclerView.Adapter<ListPresensiAdapterInstruktur.KelasViewHolder>() {

    inner class KelasViewHolder(item:ListPresensiKelasInstrukturBinding):RecyclerView.ViewHolder(item.root){
        private val binding = item
        fun bind(data: ListPresensiInstruktur){
            with(binding) {
                txtTgl.text = Format.formatDateTime(data.tanggal)
                txtId.text = data.id
                txtWaktuMulai.text = "${data.waktuMulai}"
                txtWaktuSelesai.text = "- ${data.waktuSelesai}"
                txtDurasi.text = "Durasi: ${data.durasi} Jam"
                txtTerlambat.text = "Terlambat : ${data.terlambat} Detik"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType:
    Int ): KelasViewHolder {
        return KelasViewHolder(ListPresensiKelasInstrukturBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,false
        ))
    }

    override fun onBindViewHolder(holder: KelasViewHolder,
                                  position: Int) {
        holder.bind(listPresensi[position])
    }

    override fun getItemCount(): Int = listPresensi.size
}