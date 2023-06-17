package com.example.mobile_gofit.member.booking.kelas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mobile_gofit.R
import com.example.mobile_gofit.databinding.ActivityBookingKelasBinding
import com.example.mobile_gofit.member.MenuActivityMember

class BookingActivityKelas : AppCompatActivity() {
    private lateinit var binding: ActivityBookingKelasBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_kelas)

        binding = ActivityBookingKelasBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showDataFragment()

        with(binding){
            btnBack.setOnClickListener{
                startActivity(Intent(this@BookingActivityKelas, BookingActivityListKelas::class.java))
                finish()
            }
        }
    }

    private fun showDataFragment() {
        val mFragmentManager = supportFragmentManager
        val mFragmentTransaction = mFragmentManager.beginTransaction()
        val mFragment = BookingFragmentKelas()
        mFragmentTransaction.replace(R.id.fl_data,mFragment).commit()
    }
}