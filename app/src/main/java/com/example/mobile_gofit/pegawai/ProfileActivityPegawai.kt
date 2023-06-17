package com.example.mobile_gofit.pegawai

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
import com.example.mobile_gofit.databinding.ActivityProfilePegawaiBinding
import com.example.mobile_gofit.retrofit.RClient
import com.example.mobile_gofit.retrofit.profile.ResponseDataPegawai

class ProfileActivityPegawai : AppCompatActivity() {
    var pref: SharedPreferences? = null
    private lateinit var binding: ActivityProfilePegawaiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_pegawai)

        binding = ActivityProfilePegawaiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pref = getSharedPreferences("data", Context.MODE_PRIVATE)
        val id = pref!!.getString("user", "")

        id?.let { getUserById(it) }

        with(binding){
            btnBack.setOnClickListener{
                startActivity(Intent(this@ProfileActivityPegawai, MenuActivityPegawai::class.java))
                finish()
            }

            btnUbahPass.setOnClickListener{
                startActivity(Intent(this@ProfileActivityPegawai, UbahPasswordActivityPegawai::class.java))
                finish()
            }
        }
    }

    private fun getUserById(id: String) {
        RClient.instances.getPegawaiById(id).enqueue(object : Callback<ResponseDataPegawai> {
            override fun onResponse( call: Call<ResponseDataPegawai>,  response: Response<ResponseDataPegawai> ) {
                if(response.isSuccessful){
                    val user = response.body()

                    with(binding){
                        NamaProfil.text = (user?.nama)
                        AlamatProfil.text = user?.alamat
                        TanggalLahirProfil.text = (Format.formatDate(user?.tanggalLahir))
                        NomorTeleponProfil.text = (user?.noTelepon)
                    }
                }
            }

            override fun onFailure(call: Call<ResponseDataPegawai>, t: Throwable) {
                Toast.makeText(
                    this@ProfileActivityPegawai,
                    t.localizedMessage,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}