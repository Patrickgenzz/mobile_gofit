package com.example.mobile_gofit.member.booking.gym

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_gofit.databinding.ListDataBookingGymBinding
import com.example.mobile_gofit.member.MenuActivityMember
import com.example.mobile_gofit.retrofit.RClient
import com.example.mobile_gofit.retrofit.booking.gym.ListBookingGym
import com.example.mobile_gofit.retrofit.booking.gym.ResponseBatalBookingGym
import com.example.mobile_gofit.utils.Format
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookingAdapterGym (
    private val listGym:ArrayList<ListBookingGym>,
    private val context: Context
):RecyclerView.Adapter<BookingAdapterGym.GymViewHolder>() {
    inner class GymViewHolder(item:ListDataBookingGymBinding):RecyclerView.ViewHolder(item.root){
        private val binding = item
        fun bind(dataGym: ListBookingGym){
            with(binding) {
                txtId.text = dataGym.id
//                txtTglBooking.text =  "Waktu Booking: ${Format.formatDate(dataGym.tanggalBooking)}"
                txtTglDiBooking.text =  Format.formatDateTime(dataGym.tanggalDiBooking)
                txtSlot.text = "Slot Waktu ${dataGym.slot} Jam"

                btnBatal.setOnClickListener {
                    val materialAlertDialogBuilder = MaterialAlertDialogBuilder(context)
                    materialAlertDialogBuilder.setTitle("Konfirmasi")
                        .setMessage("Apakah Anda Yakin Ingin Membatalkan Booking Gym Ini?")
                        .setNegativeButton("Kembali", null)
                        .setPositiveButton("Batalkan") { _, _ ->
                            batalBookingGym(dataGym.id)
                        }
                        .show()
                }
            }
        }
    }

    private fun batalBookingGym(id: String){
        RClient.instances.batalBookingGym(id).enqueue(object :
            Callback<ResponseBatalBookingGym> {
            override fun onResponse(call: Call<ResponseBatalBookingGym>, response: Response<ResponseBatalBookingGym>) {
                if(response.isSuccessful){
                    Toast.makeText(context, "Berhasil Batal Booking Gym!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(context, MenuActivityMember::class.java)
                    context.startActivity(intent)
                }else if(response.code() == 404){
                    Toast.makeText(context, "Maksimal Batal H-1!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(context, MenuActivityMember::class.java)
                    context.startActivity(intent)
                }else{
                    Toast.makeText(context, "Gagal Batal Booking Gym!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(context, MenuActivityMember::class.java)
                    context.startActivity(intent)
                }
            }

            override fun onFailure(call: Call<ResponseBatalBookingGym>, t: Throwable) {
                Toast.makeText(
                    context,
                    t.localizedMessage,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType:
    Int): GymViewHolder {
        return GymViewHolder(ListDataBookingGymBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,false
        ))
    }

    override fun onBindViewHolder(holder: GymViewHolder,
                                  position: Int) {
        holder.bind(listGym[position])
    }

    override fun getItemCount(): Int = listGym.size
}