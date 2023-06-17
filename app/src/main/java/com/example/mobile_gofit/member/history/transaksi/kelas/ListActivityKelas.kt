package com.example.mobile_gofit.member.history.transaksi.kelas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mobile_gofit.R
import com.example.mobile_gofit.databinding.ActivityListAktivasiBinding
import com.example.mobile_gofit.databinding.ActivityListKelasBinding
import com.example.mobile_gofit.member.history.MenuActivityHistoryMember

class ListActivityKelas : AppCompatActivity() {
    private lateinit var binding: ActivityListKelasBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_kelas)

        binding = ActivityListKelasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showDataFragment()

        with(binding){
           btnBack.setOnClickListener{
               startActivity(Intent(this@ListActivityKelas, MenuActivityHistoryMember::class.java))
               finish()
           }
        }
    }

    private fun showDataFragment() {
        val mFragmentManager = supportFragmentManager
        val mFragmentTransaction = mFragmentManager.beginTransaction()
        val mFragment = ListFragmentKelas()
        mFragmentTransaction.replace(R.id.fl_data,mFragment).commit()
    }
}