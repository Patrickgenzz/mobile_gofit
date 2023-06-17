package com.example.mobile_gofit.instruktur.izin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import com.example.mobile_gofit.R
import com.example.mobile_gofit.databinding.ActivityIzinInstrukturBinding
import com.example.mobile_gofit.instruktur.MenuActivityInstruktur

class IzinActivityInstruktur : AppCompatActivity() {
    private lateinit var binding: ActivityIzinInstrukturBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_izin_instruktur)

        binding = ActivityIzinInstrukturBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showDataFragment()

        with(binding){
           btnBack.setOnClickListener{
               startActivity(Intent(this@IzinActivityInstruktur, MenuActivityInstruktur::class.java))
               finish()
           }

           btnAdd.setOnClickListener{
               startActivity(Intent(this@IzinActivityInstruktur, TambahActivityIzin::class.java))
               finish()
           }
        }
    }

    private fun showDataFragment() {
        val mFragmentManager = supportFragmentManager
        val mFragmentTransaction = mFragmentManager.beginTransaction()
        val mFragment = IzinFragmentInstruktur()
        mFragmentTransaction.replace(R.id.fl_data,mFragment).commit()
    }
}