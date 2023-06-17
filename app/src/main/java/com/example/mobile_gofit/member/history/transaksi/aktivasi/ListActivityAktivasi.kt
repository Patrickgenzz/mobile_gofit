package com.example.mobile_gofit.member.history.transaksi.aktivasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mobile_gofit.R
import com.example.mobile_gofit.databinding.ActivityListAktivasiBinding
import com.example.mobile_gofit.member.history.MenuActivityHistoryMember

class ListActivityAktivasi : AppCompatActivity() {
    private lateinit var binding: ActivityListAktivasiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_aktivasi)

        binding = ActivityListAktivasiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showDataFragment()

        with(binding){
           btnBack.setOnClickListener{
               startActivity(Intent(this@ListActivityAktivasi, MenuActivityHistoryMember::class.java))
               finish()
           }
        }
    }

    private fun showDataFragment() {
        val mFragmentManager = supportFragmentManager
        val mFragmentTransaction = mFragmentManager.beginTransaction()
        val mFragment = ListFragmentAktivasi()
        mFragmentTransaction.replace(R.id.fl_data,mFragment).commit()
    }
}