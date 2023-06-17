package com.example.mobile_gofit.instruktur.presensi

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_gofit.databinding.ListPresensiMemberBinding
import com.example.mobile_gofit.utils.Format
import android.widget.Toast
import com.example.mobile_gofit.instruktur.MenuActivityInstruktur
import com.example.mobile_gofit.member.MenuActivityMember
import com.example.mobile_gofit.pegawai.MenuActivityPegawai
import com.example.mobile_gofit.retrofit.RClient
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.example.mobile_gofit.retrofit.presensi.instruktur.ListKelasHariIni
import com.example.mobile_gofit.retrofit.presensi.instruktur.PresensiInstrukturRequest
import com.example.mobile_gofit.retrofit.presensi.instruktur.ResponsePresensiInstrukturRequest
import com.example.mobile_gofit.retrofit.presensi.member.ListKelasMember
import com.example.mobile_gofit.retrofit.presensi.member.ResponsePresensiMemberRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PresensiAdapterMember (
    private val listMember:ArrayList<ListKelasMember>,
    private val context: Context,
):RecyclerView.Adapter<PresensiAdapterMember.KelasViewHolder>() {

    inner class KelasViewHolder(item:ListPresensiMemberBinding):RecyclerView.ViewHolder(item.root){
        private val binding = item
        fun bind(dataMember: ListKelasMember){
            with(binding) {
                txtTgl.text = Format.formatDateTime(dataMember.tanggal)
                txtNama.text = dataMember.nama
                txtKelas.text = dataMember.jenis
                txtHari.text = dataMember.hari
                txtStatus.text = dataMember.status

                btnPresensi.setOnClickListener {
                    val materialAlertDialogBuilder = MaterialAlertDialogBuilder(context)
                    materialAlertDialogBuilder.setTitle("Konfirmasi")
                        .setMessage("Apakah Anda Yakin Ingin Melakukan Presensi?")
                        .setNegativeButton("Batal", null)
                        .setPositiveButton("Presensi") { _, _ ->
                             presensiMember(dataMember.id)
                        }
                        .show()
                }
            }
        }
    }

    private fun presensiMember(idBooking: String){
        RClient.instances.updatePresensiMember(idBooking).enqueue(object :
            Callback<ResponsePresensiMemberRequest> {
            override fun onResponse(call: Call<ResponsePresensiMemberRequest>, response: Response<ResponsePresensiMemberRequest>) {
                if(response.isSuccessful){
                    Toast.makeText(context, "Berhasil Presensi Member!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(context, MenuActivityInstruktur::class.java)
                    context.startActivity(intent)
                }else if(response.code() == 406){
                    Toast.makeText(context, "Member Sudah Dipresensi!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(context, MenuActivityInstruktur::class.java)
                    context.startActivity(intent)
                }else if(response.code() == 402){
                    Toast.makeText(context, "Deposit Uang Atau Kelas Member Tidak Cukup!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(context, MenuActivityInstruktur::class.java)
                    context.startActivity(intent)
                }else if(response.code() == 403){
                    Toast.makeText(context, "Instruktur Belum Di Presensi!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(context, MenuActivityInstruktur::class.java)
                    context.startActivity(intent)
                }else{
                    Toast.makeText(context, "Gagal Presensi Member!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(context, MenuActivityInstruktur::class.java)
                    context.startActivity(intent)
                }
            }

            override fun onFailure(call: Call<ResponsePresensiMemberRequest>, t: Throwable) {
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
        return KelasViewHolder(ListPresensiMemberBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,false
        ))
    }

    override fun onBindViewHolder(holder: KelasViewHolder,
                                  position: Int) {
        holder.bind(listMember[position])
    }

    override fun getItemCount(): Int = listMember.size
}