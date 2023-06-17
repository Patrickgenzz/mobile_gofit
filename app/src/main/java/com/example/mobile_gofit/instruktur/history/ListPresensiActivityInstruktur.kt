package com.example.mobile_gofit.instruktur.history

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mobile_gofit.R
import com.example.mobile_gofit.databinding.ActivityListPresensiInstrukturBinding
import com.example.mobile_gofit.instruktur.MenuActivityInstruktur
import com.example.mobile_gofit.instruktur.ProfileActivityInstruktur

class ListPresensiActivityInstruktur : AppCompatActivity() {
    private lateinit var binding: ActivityListPresensiInstrukturBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_presensi_instruktur)

        binding = ActivityListPresensiInstrukturBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showDataFragment()

        with(binding){
           btnBack.setOnClickListener{
               startActivity(Intent(this@ListPresensiActivityInstruktur, ProfileActivityInstruktur::class.java))
               finish()
           }
        }
    }

    private fun showDataFragment() {
        val mFragmentManager = supportFragmentManager
        val mFragmentTransaction = mFragmentManager.beginTransaction()
        val mFragment = ListPresensiFragmentInstruktur()
        mFragmentTransaction.replace(R.id.fl_data,mFragment).commit()
    }
}