package com.example.mobile_gofit.member

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
import com.example.mobile_gofit.databinding.ActivityProfileMemberBinding
import com.example.mobile_gofit.member.history.MenuActivityHistoryMember
import com.example.mobile_gofit.retrofit.RClient
import com.example.mobile_gofit.retrofit.profile.ResponseDataMember

class ProfileActivityMember : AppCompatActivity() {
    var pref: SharedPreferences? = null
    private lateinit var binding: ActivityProfileMemberBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_member)

        binding = ActivityProfileMemberBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pref = getSharedPreferences("data", Context.MODE_PRIVATE)
        val id = pref!!.getString("user", "")

        id?.let { getUserById(it) }

        with(binding){
            btnBack.setOnClickListener{
                startActivity(Intent(this@ProfileActivityMember, MenuActivityMember::class.java))
                finish()
            }

            btnHistory.setOnClickListener{
                startActivity(Intent(this@ProfileActivityMember, MenuActivityHistoryMember::class.java))
                finish()
            }
        }
    }

    private fun getUserById(id: String) {
        RClient.instances.getMemberById(id).enqueue(object : Callback<ResponseDataMember> {
            override fun onResponse( call: Call<ResponseDataMember>, response: Response<ResponseDataMember>) {
                if(response.isSuccessful){
                    val user = response.body()

                    with(binding) {
                        NamaProfil.text = user?.nama
                        AlamatProfil.text = user?.alamat
                        TanggalLahirProfil.text = Format.formatDate(user?.tanggalLahir)
                        NomorTeleponProfil.text = user?.noTelepon
                        SisaDepositUang.text = Format.formatRupiah(user?.depositUang)
                        SisaDepositKelas.text = user?.depositKelas.toString()
                        TanggalKadaluarsa.text = Format.formatDate(user?.tanggalKadaluarsa)
                    }
                }
            }

            override fun onFailure(call: Call<ResponseDataMember>, t: Throwable) {
                Toast.makeText(
                    this@ProfileActivityMember,
                    t.localizedMessage,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}