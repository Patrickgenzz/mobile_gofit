package com.example.mobile_gofit.member.booking.gym

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import com.example.mobile_gofit.R
import com.example.mobile_gofit.databinding.ActivityBookingGymBinding
import com.example.mobile_gofit.member.MenuActivityMember

class BookingActivityGym : AppCompatActivity() {
    private lateinit var binding: ActivityBookingGymBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_gym)

        binding = ActivityBookingGymBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showDataFragment()

        with(binding){
           btnBack.setOnClickListener{
               startActivity(Intent(this@BookingActivityGym, MenuActivityMember::class.java))
               finish()
           }

           btnAdd.setOnClickListener{
               startActivity(Intent(this@BookingActivityGym, TambahBookingGymActivity::class.java))
               finish()
           }
        }
    }

    private fun showDataFragment() {
        val mFragmentManager = supportFragmentManager
        val mFragmentTransaction = mFragmentManager.beginTransaction()
        val mFragment = BookingFragmentGym()
        mFragmentTransaction.replace(R.id.fl_data,mFragment).commit()
    }
}