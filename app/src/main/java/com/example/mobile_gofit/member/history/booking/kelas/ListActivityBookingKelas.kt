package com.example.mobile_gofit.member.history.booking.kelas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mobile_gofit.R
import com.example.mobile_gofit.databinding.ActivityListBookingKelasBinding
import com.example.mobile_gofit.member.history.MenuActivityHistoryMember

class ListActivityBookingKelas : AppCompatActivity() {
    private lateinit var binding: ActivityListBookingKelasBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_booking_kelas)

        binding = ActivityListBookingKelasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showDataFragment()

        with(binding){
           btnBack.setOnClickListener{
               startActivity(Intent(this@ListActivityBookingKelas, MenuActivityHistoryMember::class.java))
               finish()
           }
        }
    }

    private fun showDataFragment() {
        val mFragmentManager = supportFragmentManager
        val mFragmentTransaction = mFragmentManager.beginTransaction()
        val mFragment = ListFragmentBookingKelas()
        mFragmentTransaction.replace(R.id.fl_data,mFragment).commit()
    }
}