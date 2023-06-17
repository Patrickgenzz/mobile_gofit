package com.example.mobile_gofit.member.booking.gym

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mobile_gofit.R
import com.example.mobile_gofit.databinding.ActivityTambahBookingGymBinding
import com.example.mobile_gofit.retrofit.RClient
import com.example.mobile_gofit.retrofit.booking.gym.BookingGymRequest
import com.example.mobile_gofit.retrofit.booking.gym.ResponseBookingGymRequest
import com.example.mobile_gofit.utils.Format
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class TambahBookingGymActivity : AppCompatActivity() {
    var pref: SharedPreferences? = null
    private lateinit var binding: ActivityTambahBookingGymBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_booking_gym)

        pref = getSharedPreferences("data", Context.MODE_PRIVATE)
        binding = ActivityTambahBookingGymBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            btnCancel.setOnClickListener {
                startActivity(Intent(this@TambahBookingGymActivity, BookingActivityGym::class.java))
                finish()
            }

            btnSave.setOnClickListener {
                tambahBookingKelas()
            }

            inputTglBooking.setOnClickListener {
                val calendar = Calendar.getInstance()
                val year = calendar.get(Calendar.YEAR)
                val month = calendar.get(Calendar.MONTH)
                val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

                val datePicker = DatePickerDialog(
                    this@TambahBookingGymActivity, DatePickerDialog.OnDateSetListener { _, year, month, day ->
                        val hour = calendar.get(Calendar.HOUR_OF_DAY)
                        val minute = calendar.get(Calendar.MINUTE)

                        val timePicker = TimePickerDialog(
                            this@TambahBookingGymActivity, TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                                val formattedDateTime = Format.dateToString(year, month, day, hourOfDay, minute)
                                inputTglBooking.setText(formattedDateTime)
                            }, hour, minute, true
                        )
                        timePicker.show()
                    }, year, month, dayOfMonth
                )
                datePicker.show()
            }
        }
    }

    private fun tambahBookingKelas() {
        with(binding){
            val tglBooking: String = inputTglBooking.text.toString()

            val id = pref!!.getString("user", "")
            val request = BookingGymRequest()
            request.id = id
            request.tanggal = tglBooking

            RClient.instances.addBookingGym(request).enqueue(object : Callback<ResponseBookingGymRequest>{
                override fun onResponse(call: Call<ResponseBookingGymRequest>, response: Response<ResponseBookingGymRequest> ) {
                    if(response.isSuccessful){
                        Toast.makeText(this@TambahBookingGymActivity, "Berhasil Menambah Booking Gym!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@TambahBookingGymActivity, BookingActivityGym::class.java))
                        finish()
                    }else if(response.code() == 404) {
                        Toast.makeText(this@TambahBookingGymActivity, "Status Member Tidak Aktif!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@TambahBookingGymActivity, BookingActivityGym::class.java))
                        finish()
                    }else if(response.code() == 405){
                        Toast.makeText(this@TambahBookingGymActivity, "Tidak Bisa Booking Tanggal Yang Sudah Lewat!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@TambahBookingGymActivity, BookingActivityGym::class.java))
                        finish()
                    }else if(response.code() == 403){
                        Toast.makeText(this@TambahBookingGymActivity, "Tidak Bisa Booking Ditanggal Yang Sama!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@TambahBookingGymActivity, BookingActivityGym::class.java))
                        finish()
                    }else if(response.code() == 402){
                        Toast.makeText(this@TambahBookingGymActivity, "Tanggal Yang Dibooking Sudah Penuh!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@TambahBookingGymActivity, BookingActivityGym::class.java))
                        finish()
                    }else if(response.code() == 406) {
                        Toast.makeText(this@TambahBookingGymActivity, "Hanya Bisa Booking Gym Sekali Sehari!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@TambahBookingGymActivity, BookingActivityGym::class.java))
                        finish()
                    }else {
                        Toast.makeText(this@TambahBookingGymActivity, "Gagal Menambah Booking Gym!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@TambahBookingGymActivity, BookingActivityGym::class.java))
                        finish()
                    }
                }

                override fun onFailure(call: Call<ResponseBookingGymRequest>, t: Throwable) {
                   Toast.makeText(
                        this@TambahBookingGymActivity,
                        t.localizedMessage,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        }
    }
}