package com.example.mobile_gofit.pegawai.presensi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mobile_gofit.R
import com.example.mobile_gofit.databinding.ActivityPresensiInstrukturBinding
import com.example.mobile_gofit.pegawai.MenuActivityPegawai

class PresensiActivityInstruktur : AppCompatActivity() {
    private lateinit var binding: ActivityPresensiInstrukturBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_presensi_instruktur)

        binding = ActivityPresensiInstrukturBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showDataFragment()

        with(binding){
            btnBack.setOnClickListener{
                startActivity(Intent(this@PresensiActivityInstruktur, MenuActivityPegawai::class.java))
                finish()
            }
        }
    }

    private fun showDataFragment() {
        val mFragmentManager = supportFragmentManager
        val mFragmentTransaction = mFragmentManager.beginTransaction()
        val mFragment = PresensiFragmentInstruktur()
        mFragmentTransaction.replace(R.id.fl_data,mFragment).commit()
    }
}