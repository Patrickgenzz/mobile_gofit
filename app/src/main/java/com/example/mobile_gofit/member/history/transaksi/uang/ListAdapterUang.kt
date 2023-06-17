package com.example.mobile_gofit.member.history.transaksi.uang

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_gofit.databinding.ListUangBinding
import com.example.mobile_gofit.utils.Format

import com.example.mobile_gofit.retrofit.history.member.transaksi.uang.ListDepositUang

class ListAdapterUang (
    private val listData:ArrayList<ListDepositUang>,
    private val context: Context,
):RecyclerView.Adapter<ListAdapterUang.KelasViewHolder>() {

    inner class KelasViewHolder(item:ListUangBinding):RecyclerView.ViewHolder(item.root){
        private val binding = item
        fun bind(data: ListDepositUang){
            with(binding) {
                txtId.text = data.id
                txtTgl.text = "Tanggal Deposit: ${Format.formatDateTime(data.tanggalDeposit)}"
                txtTotal.text = "Total Deposit: ${Format.formatRupiah(data.total)}"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType:
    Int ): KelasViewHolder {
        return KelasViewHolder(ListUangBinding.inflate(
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