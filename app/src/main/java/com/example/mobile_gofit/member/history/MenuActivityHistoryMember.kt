package com.example.mobile_gofit.member.history

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import com.example.mobile_gofit.R
import com.example.mobile_gofit.databinding.ActivityMenuHistoryMemberBinding
import com.example.mobile_gofit.member.ProfileActivityMember
import com.example.mobile_gofit.member.history.booking.gym.ListActivityBookingGym
import com.example.mobile_gofit.member.history.booking.kelas.ListActivityBookingKelas
import com.example.mobile_gofit.member.history.transaksi.aktivasi.ListActivityAktivasi
import com.example.mobile_gofit.member.history.transaksi.kelas.ListActivityKelas
import com.example.mobile_gofit.member.history.transaksi.uang.ListActivityUang

class MenuActivityHistoryMember : AppCompatActivity() {
    var pref: SharedPreferences? = null
    private lateinit var binding: ActivityMenuHistoryMemberBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_history_member)

        binding = ActivityMenuHistoryMemberBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pref = getSharedPreferences("data", Context.MODE_PRIVATE)
        setUpCardView()
    }

    private fun setUpCardView() {
        with(binding){
            btnAktivasi.setOnClickListener {
                startActivity(Intent(this@MenuActivityHistoryMember, ListActivityAktivasi::class.java))
                finish()
            }

            btnDepositUang.setOnClickListener {
                startActivity(Intent(this@MenuActivityHistoryMember, ListActivityUang::class.java))
                finish()
            }

            btnDepositKelas.setOnClickListener {
                startActivity(Intent(this@MenuActivityHistoryMember, ListActivityKelas::class.java))
                finish()
            }

            btnBookingKelas.setOnClickListener {
                startActivity(Intent(this@MenuActivityHistoryMember, ListActivityBookingKelas::class.java))
                finish()
            }

            btnBookingGym.setOnClickListener {
                startActivity(Intent(this@MenuActivityHistoryMember, ListActivityBookingGym::class.java))
                finish()
            }

            btnBack.setOnClickListener {
                startActivity(Intent(this@MenuActivityHistoryMember, ProfileActivityMember::class.java))
                finish()
            }
        }
    }
}