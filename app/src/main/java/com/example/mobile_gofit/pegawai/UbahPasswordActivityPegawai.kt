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

import com.example.mobile_gofit.R
import com.example.mobile_gofit.databinding.ActivityUbahPasswordPegawaiBinding
import com.example.mobile_gofit.retrofit.RClient
import com.example.mobile_gofit.retrofit.ResponseUpdate
import com.example.mobile_gofit.retrofit.profile.PasswordRequest

class UbahPasswordActivityPegawai : AppCompatActivity() {
    var pref: SharedPreferences? = null
    private lateinit var binding: ActivityUbahPasswordPegawaiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ubah_password_pegawai)

        binding = ActivityUbahPasswordPegawaiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pref = getSharedPreferences("data", Context.MODE_PRIVATE)

        with(binding){
            btnCancel.setOnClickListener {
                startActivity(Intent(this@UbahPasswordActivityPegawai, ProfileActivityPegawai::class.java))
                finish()
            }

            btnSave.setOnClickListener {
                ubahPassword()
            }
        }
    }

    private fun ubahPassword() {
        with(binding){
            val lama: String = inputPasswordLama.text.toString()
            val baru: String = inputPasswordBaru.text.toString()
            val konfirmasi: String = inputKonfirmasiPasswordBaru.text.toString()

            if(lama.isEmpty()){
                Toast.makeText(this@UbahPasswordActivityPegawai, "Password Lama Tidak Boleh Kosong!", Toast.LENGTH_SHORT).show()
                inputPasswordLama.error = "Password Lama Tidak Boleh Kosong!"
                return
            }
            if(baru.isEmpty()){
                Toast.makeText(this@UbahPasswordActivityPegawai, "Password Baru Tidak Boleh Kosong!", Toast.LENGTH_SHORT).show()
                inputPasswordBaru.error = "Password Baru Tidak Boleh Kosong!"
                return
            }
            if(konfirmasi.isEmpty()){
                Toast.makeText(this@UbahPasswordActivityPegawai, "Konfirmasi Password Baru Tidak Boleh Kosong!", Toast.LENGTH_SHORT).show()
                inputKonfirmasiPasswordBaru.error = "Konfirmasi Password Baru Tidak Boleh Kosong!"
                return
            }
            if(baru != (konfirmasi)){
                Toast.makeText(this@UbahPasswordActivityPegawai, "Konfirmasi Password Salah!", Toast.LENGTH_SHORT).show()
                return
            }
            if(baru == (lama)){
                Toast.makeText(this@UbahPasswordActivityPegawai, "Password Baru Tidak Boleh Sama Dengan Password Lama!", Toast.LENGTH_SHORT).show()
                return
            }

            val email = pref!!.getString("email", "")
            val request = PasswordRequest()
            request.passwordBaru = baru
            request.passwordLama = lama
            request.email = email

            RClient.instances.postGantiPasswordPegawai(request).enqueue(object : Callback<ResponseUpdate> {
                override fun onResponse(call: Call<ResponseUpdate>, response: Response<ResponseUpdate>) {
                    if(response.isSuccessful){
                        Toast.makeText(this@UbahPasswordActivityPegawai, "Berhasil Mengganti Password!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@UbahPasswordActivityPegawai, ProfileActivityPegawai::class.java))
                        finish()
                    }else{
                        Toast.makeText(this@UbahPasswordActivityPegawai, "Password Lama Salah!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@UbahPasswordActivityPegawai, ProfileActivityPegawai::class.java))
                        finish()
                    }
                }

                override fun onFailure(call: Call<ResponseUpdate>, t: Throwable) {
                    Toast.makeText(
                        this@UbahPasswordActivityPegawai,
                        t.localizedMessage,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        }
    }
}