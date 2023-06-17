package com.example.mobile_gofit.pegawai.presensi

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_gofit.databinding.ListPresensiInstrukturBinding
import com.example.mobile_gofit.retrofit.booking.kelas.ListBookingKelas
import com.example.mobile_gofit.utils.Format
import android.widget.Toast
import com.example.mobile_gofit.member.MenuActivityMember
import com.example.mobile_gofit.pegawai.MenuActivityPegawai
import com.example.mobile_gofit.retrofit.RClient
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.example.mobile_gofit.retrofit.booking.kelas.BookingKelasRequest
import com.example.mobile_gofit.retrofit.booking.kelas.ResponseBookingKelasRequest
import com.example.mobile_gofit.retrofit.presensi.instruktur.ListKelasHariIni
import com.example.mobile_gofit.retrofit.presensi.instruktur.PresensiInstrukturRequest
import com.example.mobile_gofit.retrofit.presensi.instruktur.ResponsePresensiInstrukturRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PresensiAdapterInstruktur (
    private val listKelasHariIni:ArrayList<ListKelasHariIni>,
    private val context: Context,
):RecyclerView.Adapter<PresensiAdapterInstruktur.KelasViewHolder>() {

    inner class KelasViewHolder(item:ListPresensiInstrukturBinding):RecyclerView.ViewHolder(item.root){
        private val binding = item
        fun bind(dataKelasHariIni: ListKelasHariIni){
            with(binding) {
                txtTgl.text = Format.formatDate(dataKelasHariIni.tanggal)
                txtNama.text = dataKelasHariIni.nama
                txtKelas.text = dataKelasHariIni.jenis
                txtHari.text = "${dataKelasHariIni.hari},"
                if(dataKelasHariIni.sesi == 1){
                    txtSesi.text = "Jam 08:00"
                }else if(dataKelasHariIni.sesi == 2){
                    txtSesi.text = "Jam 09:30"
                }else if(dataKelasHariIni.sesi == 3){
                    txtSesi.text = "Jam 17:00"
                }else{
                    txtSesi.text = "Jam 18:30"
                }
                txtStatus.text = dataKelasHariIni.status

                btnPresensi.setOnClickListener {
                    val materialAlertDialogBuilder = MaterialAlertDialogBuilder(context)
                    materialAlertDialogBuilder.setTitle("Konfirmasi")
                        .setMessage("Apakah Anda Yakin Ingin Melakukan Presensi?")
                        .setNegativeButton("Batal", null)
                        .setPositiveButton("Presensi") { _, _ ->
                             presensiInstruktur(dataKelasHariIni.tanggal, dataKelasHariIni.id)
                        }
                        .show()
                }
            }
        }
    }

    private fun presensiInstruktur(tanggal: String, idInstruktur: String){
        val request = PresensiInstrukturRequest()
        request.id = idInstruktur
        request.tanggal = tanggal

        RClient.instances.addPresensiInstruktur(request).enqueue(object :
            Callback<ResponsePresensiInstrukturRequest> {
            override fun onResponse(call: Call<ResponsePresensiInstrukturRequest>, response: Response<ResponsePresensiInstrukturRequest>) {
                if(response.isSuccessful){
                    Toast.makeText(context, "Berhasil Presensi Instruktur!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(context, MenuActivityPegawai::class.java)
                    context.startActivity(intent)
                }else if(response.code() == 406){
                    Toast.makeText(context, "Instruktur Sudah Dipresensi!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(context, MenuActivityPegawai::class.java)
                    context.startActivity(intent)
                }else{
                    Toast.makeText(context, "Gagal Presensi Instruktur!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(context, MenuActivityPegawai::class.java)
                    context.startActivity(intent)
                }
            }

            override fun onFailure(call: Call<ResponsePresensiInstrukturRequest>, t: Throwable) {
                Toast.makeText(
                    context,
                    t.localizedMessage,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType:
    Int ): KelasViewHolder {
        return KelasViewHolder(ListPresensiInstrukturBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,false
        ))
    }

    override fun onBindViewHolder(holder: KelasViewHolder,
                                  position: Int) {
        holder.bind(listKelasHariIni[position])
    }

    override fun getItemCount(): Int = listKelasHariIni.size
}