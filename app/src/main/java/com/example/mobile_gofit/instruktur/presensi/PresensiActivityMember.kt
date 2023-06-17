package com.example.mobile_gofit.instruktur.presensi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mobile_gofit.R
import com.example.mobile_gofit.databinding.ActivityPresensiMemberBinding
import com.example.mobile_gofit.instruktur.MenuActivityInstruktur
import com.example.mobile_gofit.pegawai.MenuActivityPegawai

class PresensiActivityMember : AppCompatActivity() {
    private lateinit var binding: ActivityPresensiMemberBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_presensi_member)

        binding = ActivityPresensiMemberBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showDataFragment()

        with(binding){
            btnBack.setOnClickListener{
                startActivity(Intent(this@PresensiActivityMember, MenuActivityInstruktur::class.java))
                finish()
            }
        }
    }

    private fun showDataFragment() {
        val mFragmentManager = supportFragmentManager
        val mFragmentTransaction = mFragmentManager.beginTransaction()
        val mFragment = PresensiFragmentMember()
        mFragmentTransaction.replace(R.id.fl_data,mFragment).commit()
    }
}