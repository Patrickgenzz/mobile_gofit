package com.example.mobile_gofit.instruktur.izin

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_gofit.databinding.ListDataIzinBinding
import com.example.mobile_gofit.retrofit.instruktur.IzinInstruktur
import com.example.mobile_gofit.utils.Format

class IzinAdapterInstruktur (
    private val listIzin:ArrayList<IzinInstruktur>,
    private val context: Context
):RecyclerView.Adapter<IzinAdapterInstruktur.InstrukturViewHolder>() {
    inner class InstrukturViewHolder(item:ListDataIzinBinding):RecyclerView.ViewHolder(item.root){
        private val binding = item
        fun bind(dataIzinInstruktur: IzinInstruktur){
            with(binding) {
                txtTgl.text = Format.formatDate(dataIzinInstruktur.tanggalIzin)
                txtAlasan.text = dataIzinInstruktur.alasanIzin
                txtStatus.text = dataIzinInstruktur.status
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType:
    Int): InstrukturViewHolder {
        return InstrukturViewHolder(ListDataIzinBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,false
        ))
    }

    override fun onBindViewHolder(holder: InstrukturViewHolder,
                                  position: Int) {
        holder.bind(listIzin[position])
    }

    override fun getItemCount(): Int = listIzin.size
}