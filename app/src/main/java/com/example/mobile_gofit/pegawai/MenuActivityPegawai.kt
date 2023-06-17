package com.example.mobile_gofit.pegawai

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Context
import android.content.Intent
import android.widget.Toast
import android.content.SharedPreferences
import com.example.mobile_gofit.LoginActivity
import com.example.mobile_gofit.R
import com.example.mobile_gofit.databinding.ActivityMenuPegawaiBinding
import com.example.mobile_gofit.pegawai.presensi.PresensiActivityInstruktur
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MenuActivityPegawai : AppCompatActivity() {
    var pref: SharedPreferences? = null
    private lateinit var binding: ActivityMenuPegawaiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_pegawai)

        binding = ActivityMenuPegawaiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pref = getSharedPreferences("data", Context.MODE_PRIVATE)
        setUpCardView()
    }

    private fun setUpCardView() {
        val role = pref!!.getString("role", "")

        with(binding){
            btnProfile.setOnClickListener {
                startActivity(Intent(this@MenuActivityPegawai, ProfileActivityPegawai::class.java))
                finish()
            }

            btnPresensiInstruktur.setOnClickListener {
                if (role != "MO") {
                    Toast.makeText(this@MenuActivityPegawai, "Hanya MO Yang Bisa Presensi!", Toast.LENGTH_SHORT).show()
                } else {
                    startActivity(Intent(this@MenuActivityPegawai, PresensiActivityInstruktur::class.java))
                    finish()
                }
            }

            btnLogout.setOnClickListener {
                val materialAlertDialogBuilder = MaterialAlertDialogBuilder(this@MenuActivityPegawai)
                materialAlertDialogBuilder.setTitle("Konfirmasi")
                    .setMessage("Apakah Anda Yakin Ingin Keluar?")
                    .setNegativeButton("Batal", null)
                    .setPositiveButton("Keluar"){_,_ ->
                        val edit : SharedPreferences.Editor = pref!!.edit()
                        edit.clear()
                        edit.apply()
                        startActivity(Intent(this@MenuActivityPegawai, LoginActivity::class.java))
                        finish()
                    }.show()
            }
        }
    }
}