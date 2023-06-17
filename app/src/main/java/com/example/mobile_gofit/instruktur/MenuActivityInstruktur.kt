package com.example.mobile_gofit.instruktur

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import com.example.mobile_gofit.instruktur.izin.IzinActivityInstruktur
import com.example.mobile_gofit.LoginActivity
import com.example.mobile_gofit.R
import com.example.mobile_gofit.databinding.ActivityMenuInstrukturBinding
import com.example.mobile_gofit.instruktur.presensi.PresensiActivityMember
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MenuActivityInstruktur : AppCompatActivity() {
    var pref: SharedPreferences? = null
    private lateinit var binding: ActivityMenuInstrukturBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_instruktur)

        binding = ActivityMenuInstrukturBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pref = getSharedPreferences("data", Context.MODE_PRIVATE)
        setUpCardView()
    }

    private fun setUpCardView() {
        with(binding){
            btnPofile.setOnClickListener {
                startActivity(Intent(this@MenuActivityInstruktur, ProfileActivityInstruktur::class.java))
                finish()
            }

            btnIzin.setOnClickListener {
                startActivity(Intent(this@MenuActivityInstruktur, IzinActivityInstruktur::class.java))
                finish()
            }

            btnPresensi.setOnClickListener {
                startActivity(Intent(this@MenuActivityInstruktur, PresensiActivityMember::class.java))
                finish()
            }

            btnLogout.setOnClickListener {
                val materialAlertDialogBuilder = MaterialAlertDialogBuilder(this@MenuActivityInstruktur)
                materialAlertDialogBuilder.setTitle("Konfirmasi")
                    .setMessage("Apakah Anda Yakin Ingin Keluar?")
                    .setNegativeButton("Batal", null)
                    .setPositiveButton("Keluar"){_,_ ->
                        val edit : SharedPreferences.Editor = pref!!.edit()
                        edit.clear()
                        edit.apply()
                        startActivity(Intent(this@MenuActivityInstruktur, LoginActivity::class.java))
                        finish()
                    }.show()
            }
        }
    }
}