package com.example.mobile_gofit.member.history.transaksi.aktivasi

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_gofit.databinding.ListAktivasiBinding
import com.example.mobile_gofit.utils.Format

import com.example.mobile_gofit.retrofit.history.member.transaksi.aktivasi.ListAktivasi

class ListAdapterAktivasi (
    private val listData:ArrayList<ListAktivasi>,
    private val context: Context,
):RecyclerView.Adapter<ListAdapterAktivasi.KelasViewHolder>() {

    inner class KelasViewHolder(item:ListAktivasiBinding):RecyclerView.ViewHolder(item.root){
        private val binding = item
        fun bind(data: ListAktivasi){
            with(binding) {
                txtId.text = data.id
                txtTgl.text = "Tanggal Aktivasi: ${Format.formatDateTime(data.tanggal)}"
                txtJumlah.text = "Jumlah Pembayaran: ${Format.formatRupiah(data.jumlah)}"
                txtMasa.text = "Masa Berlaku: ${Format.formatDateTime(data.masa)}"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType:
    Int ): KelasViewHolder {
        return KelasViewHolder(ListAktivasiBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,false
        ))
    }

    override fun onBindViewHolder(holder: KelasViewHolder,
                                  position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int = listData.size
}