package com.example.mobile_gofit.member

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.example.mobile_gofit.LoginActivity
import com.example.mobile_gofit.R
import com.example.mobile_gofit.member.booking.gym.BookingActivityGym
import com.example.mobile_gofit.databinding.ActivityMenuMemberBinding
import com.example.mobile_gofit.member.booking.kelas.BookingActivityListKelas
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MenuActivityMember : AppCompatActivity() {
    var pref: SharedPreferences? = null
    private lateinit var binding: ActivityMenuMemberBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_member)

        binding = ActivityMenuMemberBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pref = getSharedPreferences("data", Context.MODE_PRIVATE)
        setUpCardView()
    }

    private fun setUpCardView() {
        with(binding){
            btnProfile.setOnClickListener {
                startActivity(Intent(this@MenuActivityMember, ProfileActivityMember::class.java))
                finish()
            }

            btnBookingKelas.setOnClickListener {
                startActivity(Intent(this@MenuActivityMember, BookingActivityListKelas::class.java))
                finish()
            }

            btnBookingGym.setOnClickListener {
                startActivity(Intent(this@MenuActivityMember, BookingActivityGym::class.java))
                finish()
            }

            btnLogout.setOnClickListener {
                val materialAlertDialogBuilder = MaterialAlertDialogBuilder(this@MenuActivityMember)
                materialAlertDialogBuilder.setTitle("Konfirmasi")
                    .setMessage("Apakah Anda Yakin Ingin Keluar?")
                    .setNegativeButton("Batal", null)
                    .setPositiveButton("Keluar"){_,_ ->
                        val edit : SharedPreferences.Editor = pref!!.edit()
                        edit.clear()
                        edit.apply()
                        startActivity(Intent(this@MenuActivityMember, LoginActivity::class.java))
                        finish()
                    }.show()
            }
        }
    }
}