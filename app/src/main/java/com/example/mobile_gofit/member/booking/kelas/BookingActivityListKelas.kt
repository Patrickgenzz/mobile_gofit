package com.example.mobile_gofit.member.booking.kelas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mobile_gofit.R
import com.example.mobile_gofit.databinding.ActivityBookingListKelasBinding
import com.example.mobile_gofit.member.MenuActivityMember

class BookingActivityListKelas : AppCompatActivity() {
    private lateinit var binding: ActivityBookingListKelasBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_list_kelas)

        binding = ActivityBookingListKelasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showDataFragment()

        with(binding){
            btnBack.setOnClickListener{
               startActivity(Intent(this@BookingActivityListKelas, MenuActivityMember::class.java))
               finish()
            }
            btnAdd.setOnClickListener{
                startActivity(Intent(this@BookingActivityListKelas, BookingActivityKelas::class.java))
                finish()
            }
        }
    }

    private fun showDataFragment() {
        val mFragmentManager = supportFragmentManager
        val mFragmentTransaction = mFragmentManager.beginTransaction()
        val mFragment = BookingFragmentListKelas()
        mFragmentTransaction.replace(R.id.fl_data,mFragment).commit()
    }
}