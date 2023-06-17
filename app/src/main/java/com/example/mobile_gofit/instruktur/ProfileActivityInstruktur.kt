package com.example.mobile_gofit.instruktur

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import com.example.mobile_gofit.utils.Format
import com.example.mobile_gofit.R
import com.example.mobile_gofit.databinding.ActivityProfileInstrukturBinding
import com.example.mobile_gofit.instruktur.history.ListPresensiActivityInstruktur
import com.example.mobile_gofit.retrofit.RClient
import com.example.mobile_gofit.retrofit.profile.ResponseDataInstruktur

class ProfileActivityInstruktur : AppCompatActivity() {
    var pref: SharedPreferences? = null
    private lateinit var binding: ActivityProfileInstrukturBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_instruktur)

        binding = ActivityProfileInstrukturBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pref = getSharedPreferences("data", Context.MODE_PRIVATE)
        val id = pref!!.getString("user", "")

        id?.let { getUserById(it) }

        with(binding){
            btnBack.setOnClickListener{
                startActivity(Intent(this@ProfileActivityInstruktur, MenuActivityInstruktur::class.java))
                finish()
            }

            btnUbahPass.setOnClickListener{
                startActivity(Intent(this@ProfileActivityInstruktur, UbahPasswordActivityInstruktur::class.java))
                finish()
            }

            btnHistory.setOnClickListener {
                startActivity(Intent(this@ProfileActivityInstruktur, ListPresensiActivityInstruktur::class.java))
                finish()
            }
        }
    }

    private fun getUserById(id: String) {
        RClient.instances.getInstrukturById(id).enqueue(object : Callback<ResponseDataInstruktur> {
            override fun onResponse( call: Call<ResponseDataInstruktur>, response: Response<ResponseDataInstruktur>) {
                if(response.isSuccessful){
                    val user = response.body()

                    with(binding){
                        NamaProfil.text = user?.nama
                        AlamatProfil.text = user?.alamat
                        TanggalLahirProfil.text = (Format.formatDate(user?.tanggalLahir))
                        NomorTeleponProfil.text = user?.noTelepon
                        WaktuTerlambat.text = user?.keterlambatan.toString()
                    }
                }
            }

            override fun onFailure(call: Call<ResponseDataInstruktur>, t: Throwable) {
                Toast.makeText(
                    this@ProfileActivityInstruktur,
                    t.localizedMessage,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}