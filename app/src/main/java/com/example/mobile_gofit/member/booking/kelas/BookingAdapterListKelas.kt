package com.example.mobile_gofit.member.booking.kelas

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_gofit.databinding.BookingListKelasMemberBinding
import com.example.mobile_gofit.member.MenuActivityMember
import com.example.mobile_gofit.retrofit.RClient
import com.example.mobile_gofit.retrofit.booking.gym.ResponseBatalBookingGym
import com.example.mobile_gofit.retrofit.booking.kelas.ResponseBatalBookingKelas
import com.example.mobile_gofit.utils.Format
import java.text.SimpleDateFormat
import java.util.*

import com.example.mobile_gofit.retrofit.history.member.booking.kelas.ListBookingKelas
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookingAdapterListKelas (
    private val listBooking:ArrayList<ListBookingKelas>,
    private val context: Context,
):RecyclerView.Adapter<BookingAdapterListKelas.KelasViewHolder>() {

    inner class KelasViewHolder(item: BookingListKelasMemberBinding) : RecyclerView.ViewHolder(item.root) {
        private val binding = item
        fun bind(data: ListBookingKelas) {
            with(binding) {
                txtId.text = data.idBooking
                txtJenis.text = data.jenis
                txtTglDiBooking.text = Format.formatDateTime(data.tanggalDibooking)

                if (data.tarif > 1) {
                    txtTarif.text = "Rp.${data.tarif}"
                } else {
                    txtTarif.text = "${data.tarif}x"
                }

                btnBatal.setOnClickListener {
                    val materialAlertDialogBuilder = MaterialAlertDialogBuilder(context)
                    materialAlertDialogBuilder.setTitle("Konfirmasi")
                        .setMessage("Apakah Anda Yakin Ingin Membatalkan Booking Kelas Ini?")
                        .setNegativeButton("Kembali", null)
                        .setPositiveButton("Batalkan") { _, _ ->
                            batalBookingKelas(data.idBooking, data.idKelas)
                        }
                        .show()
                }
            }
        }
    }

    private fun batalBookingKelas(idBooking: String, idKelas: String){
        RClient.instances.batalBookingKelas(idBooking, idKelas).enqueue(object :
            Callback<ResponseBatalBookingKelas> {
            override fun onResponse(call: Call<ResponseBatalBookingKelas>, response: Response<ResponseBatalBookingKelas>) {
                if(response.isSuccessful){
                    Toast.makeText(context, "Berhasil Batal Booking Kelas!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(context, MenuActivityMember::class.java)
                    context.startActivity(intent)
                }else if(response.code() == 404){
                    Toast.makeText(context, "Maksimal Batal H-1!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(context, MenuActivityMember::class.java)
                    context.startActivity(intent)
                }else{
                    Toast.makeText(context, "Gagal Batal Booking Kelas!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(context, MenuActivityMember::class.java)
                    context.startActivity(intent)
                }
            }

            override fun onFailure(call: Call<ResponseBatalBookingKelas>, t: Throwable) {
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
        return KelasViewHolder(BookingListKelasMemberBinding.inflate(
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