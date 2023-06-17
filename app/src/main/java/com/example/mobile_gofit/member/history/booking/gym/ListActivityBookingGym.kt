package com.example.mobile_gofit.member.history.booking.gym

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mobile_gofit.R
import com.example.mobile_gofit.databinding.ActivityListBookingGymBinding
import com.example.mobile_gofit.member.history.MenuActivityHistoryMember

class ListActivityBookingGym : AppCompatActivity() {
    private lateinit var binding: ActivityListBookingGymBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_booking_gym)

        binding = ActivityListBookingGymBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showDataFragment()

        with(binding){
           btnBack.setOnClickListener{
               startActivity(Intent(this@ListActivityBookingGym, MenuActivityHistoryMember::class.java))
               finish()
           }
        }
    }

    private fun showDataFragment() {
        val mFragmentManager = supportFragmentManager
        val mFragmentTransaction = mFragmentManager.beginTransaction()
        val mFragment = ListFragmentBookingGym()
        mFragmentTransaction.replace(R.id.fl_data,mFragment).commit()
    }
}