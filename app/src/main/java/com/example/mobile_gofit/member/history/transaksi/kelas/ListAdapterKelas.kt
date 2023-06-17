package com.example.mobile_gofit.member.history.transaksi.kelas

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_gofit.databinding.ListAktivasiBinding
import com.example.mobile_gofit.databinding.ListKelasBinding
import com.example.mobile_gofit.utils.Format

import com.example.mobile_gofit.retrofit.history.member.transaksi.aktivasi.ListAktivasi
import com.example.mobile_gofit.retrofit.history.member.transaksi.kelas.ListDepositKelas

class ListAdapterKelas (
    private val listData:ArrayList<ListDepositKelas>,
    private val context: Context,
):RecyclerView.Adapter<ListAdapterKelas.KelasViewHolder>() {

    inner class KelasViewHolder(item:ListKelasBinding):RecyclerView.ViewHolder(item.root){
        private val binding = item
        fun bind(data: ListDepositKelas){
            with(binding) {
                txtId.text = data.id
                txtTgl.text = "Tanggal Deposit: ${Format.formatDateTime(data.tanggal)}"
                txtKelas.text = "Jenis Kelas: ${data.jenis}"
                txtMasa.text = "Masa Berlaku: ${Format.formatDateTime(data.masaBerlaku)}"
                txtJumlah.text = "Jumlah Pembayaran: ${Format.formatRupiah(data.jumlah)}"
                txtTotal.text = "Total Deposit Kelas: ${data.total}"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType:
    Int ): KelasViewHolder {
        return KelasViewHolder(ListKelasBinding.inflate(
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