package com.example.mobile_gofit.member.booking.kelas

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_gofit.databinding.ListDataBookingKelasBinding
import com.example.mobile_gofit.retrofit.booking.kelas.ListBookingKelas
import com.example.mobile_gofit.utils.Format
import android.widget.Toast
import com.example.mobile_gofit.member.MenuActivityMember
import com.example.mobile_gofit.retrofit.RClient
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.example.mobile_gofit.retrofit.booking.kelas.BookingKelasRequest
import com.example.mobile_gofit.retrofit.booking.kelas.ResponseBookingKelasRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookingAdapterKelas (
    private val listBookingKelas:ArrayList<ListBookingKelas>,
    private val context: Context,
    private val idUser: String
):RecyclerView.Adapter<BookingAdapterKelas.KelasViewHolder>() {

    inner class KelasViewHolder(item:ListDataBookingKelasBinding):RecyclerView.ViewHolder(item.root){
        private val binding = item
        fun bind(dataBooking: ListBookingKelas){
            with(binding) {
                txtTgl.text = Format.formatDate(dataBooking.tanggal)
                txtNama.text = dataBooking.nama
                txtKelas.text = dataBooking.jenis
                txtHari.text = "${dataBooking.hari},"
                if(dataBooking.sesi == 1){
                    txtSesi.text = "Jam 08:00"
                }else if(dataBooking.sesi == 2){
                    txtSesi.text = "Jam 09:30"
                }else if(dataBooking.sesi == 3){
                    txtSesi.text = "Jam 17:00"
                }else{
                    txtSesi.text = "Jam 18:30"
                }
                txtKuota.text = "Sisa Kuota : ${dataBooking.kuota}"
                txtTarif.text = Format.formatRupiah(dataBooking.tarif)

                btnBooking.setOnClickListener {
                    val materialAlertDialogBuilder = MaterialAlertDialogBuilder(context)
                    materialAlertDialogBuilder.setTitle("Konfirmasi")
                        .setMessage("Apakah Anda Yakin Ingin Booking Kelas Ini?")
                        .setNegativeButton("Batal", null)
                        .setPositiveButton("Booking") { _, _ ->
                             bookingKelas(dataBooking.tanggal, idUser)
                        }
                        .show()
                }
            }
        }
    }

    private fun bookingKelas(tanggal: String, idUser: String){
        val request = BookingKelasRequest()
        request.id = idUser
        request.tanggal = tanggal

        RClient.instances.addBookingKelas(request).enqueue(object :
            Callback<ResponseBookingKelasRequest> {
            override fun onResponse(call: Call<ResponseBookingKelasRequest>, response: Response<ResponseBookingKelasRequest>) {
                if(response.isSuccessful){
                    Toast.makeText(context, "Berhasil Booking Kelas!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(context, MenuActivityMember::class.java)
                    context.startActivity(intent)
                }else if(response.code() == 404){
                    Toast.makeText(context, "Status Member Tidak Aktif!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(context, MenuActivityMember::class.java)
                    context.startActivity(intent)
                }else if(response.code() == 406){
                    Toast.makeText(context, "Tidak Bisa Booking Kelas Pada Tanggal Yang Sama!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(context, MenuActivityMember::class.java)
                    context.startActivity(intent)
                }else if(response.code() == 403){
                    Toast.makeText(context, "Tidak Ada Slot Kelas!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(context, MenuActivityMember::class.java)
                    context.startActivity(intent)
                }else if(response.code() == 402){
                    Toast.makeText(context, "Sisa Deposit Uang Dan Kelas Tidak Cukup!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(context, MenuActivityMember::class.java)
                    context.startActivity(intent)
                }else{
                    Toast.makeText(context, "Gagal Booking Kelas!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(context, MenuActivityMember::class.java)
                    context.startActivity(intent)
                }
            }

            override fun onFailure(call: Call<ResponseBookingKelasRequest>, t: Throwable) {
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
        return KelasViewHolder(ListDataBookingKelasBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,false
        ))
    }

    override fun onBindViewHolder(holder: KelasViewHolder,
                                  position: Int) {
        holder.bind(listBookingKelas[position])
    }

    override fun getItemCount(): Int = listBookingKelas.size
}