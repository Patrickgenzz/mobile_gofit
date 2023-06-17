package com.example.mobile_gofit.jadwal

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_gofit.databinding.ListJadwalHarianBinding
import com.example.mobile_gofit.utils.Format
import com.example.mobile_gofit.retrofit.jadwal.ListJadwalHarian

class JadwalHarianAdapter (
    private val listJadwalHarian:ArrayList<ListJadwalHarian>,
    private val context: Context,
):RecyclerView.Adapter<JadwalHarianAdapter.JadwalViewHolder>() {

    inner class JadwalViewHolder(item:ListJadwalHarianBinding):RecyclerView.ViewHolder(item.root){
        private val binding = item
        fun bind(jadwalHarian: ListJadwalHarian){
            with(binding) {
                txtTgl.text = Format.formatDate(jadwalHarian.tanggal)
                txtNama.text = jadwalHarian.nama
                txtKelas.text = jadwalHarian.jenis
                txtHari.text = "${jadwalHarian.hari},"
                if(jadwalHarian.sesi == 1){
                    txtSesi.text = "Jam 08:00"
                }else if(jadwalHarian.sesi == 2){
                    txtSesi.text = "Jam 09:30"
                }else if(jadwalHarian.sesi == 3){
                    txtSesi.text = "Jam 17:00"
                }else{
                    txtSesi.text = "Jam 18:30"
                }
                txtStatus.text = jadwalHarian.status
                txtTarif.text = Format.formatRupiah(jadwalHarian.tarif)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType:
    Int ): JadwalViewHolder {
        return JadwalViewHolder(ListJadwalHarianBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,false
        ))
    }

    override fun onBindViewHolder(holder: JadwalViewHolder,
                                  position: Int) {
        holder.bind(listJadwalHarian[position])
    }

    override fun getItemCount(): Int = listJadwalHarian.size
}