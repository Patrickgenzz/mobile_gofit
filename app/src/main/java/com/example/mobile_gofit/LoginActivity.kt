package com.example.mobile_gofit

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import com.example.mobile_gofit.databinding.ActivityLoginBinding
import com.example.mobile_gofit.retrofit.RClient
import com.example.mobile_gofit.retrofit.login.ResponseLogin
import com.example.mobile_gofit.retrofit.login.UserRequest
import com.example.mobile_gofit.member.MenuActivityMember
import com.example.mobile_gofit.instruktur.MenuActivityInstruktur
import com.example.mobile_gofit.jadwal.JadwalHarianActivity
import com.example.mobile_gofit.pegawai.MenuActivityPegawai

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    var pref: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        pref = getSharedPreferences("data", Context.MODE_PRIVATE)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            btnLogin.setOnClickListener{
                login()
            }
            btnJadwal.setOnClickListener{
                startActivity(Intent(this@LoginActivity, JadwalHarianActivity::class.java))
                finish()
            }
        }
    }

    private fun login() {
        with(binding){
            val email: String = inputEmail.text.toString()
            val password: String = inputPassword.text.toString()

            if(email.isEmpty()){
                Toast.makeText(this@LoginActivity, "Email Tidak Boleh Kosong!", Toast.LENGTH_SHORT).show()
                inputEmail.error = "Email Tidak Boleh Kosong!"
                return
            }

            if(password.isEmpty()){
                Toast.makeText(this@LoginActivity, "Password Tidak Boleh Kosong!", Toast.LENGTH_SHORT).show()
                inputPassword.error = "Password Tidak Boleh Kosong!"
                return
            }

            //request untuk dikirim
            val request = UserRequest()
            request.email = email
            request.password = password

            RClient.instances.login(request).enqueue(object : Callback<ResponseLogin> {
                override fun onResponse( call: Call<ResponseLogin>, response: Response<ResponseLogin>) {
                    if(response.isSuccessful){
                        val user = response.body()

                        val role =  user?.role
                        val edit : SharedPreferences.Editor = pref!!.edit()

                        user?.id.let { edit.putString("id", it).commit() }
                        user?.role.let { edit.putString("role", it).commit() }
                        user?.email.let { edit.putString("email",it).commit()}
                        edit.apply()

                        Toast.makeText(this@LoginActivity, "Berhasil Login!", Toast.LENGTH_SHORT).show()
                        if(role.equals("ADMIN") or role.equals("MO") or role.equals("KASIR")){ // pegawai
                            user?.id.let { edit.putString("user", it).commit()}
                            user?.email.let { edit.putString("email",it).commit()}
                            user?.role.let { edit.putString("role", it).commit()}
                            startActivity(Intent(this@LoginActivity, MenuActivityPegawai::class.java))
                            finish()
                        }else if(role.equals("MEMBER")){ //member
                            user?.id.let { edit.putString("user", it).commit()}
                            startActivity(Intent(this@LoginActivity, MenuActivityMember::class.java))
                            finish()
                        }else if(role.equals("INSTRUKTUR")){ //instruktur
                            user?.email.let { edit.putString("email",it).commit()}
                            user?.id.let { edit.putString("user", it).commit() }
                            startActivity(Intent(this@LoginActivity, MenuActivityInstruktur::class.java))
                            finish()
                        }
                    }else if(response.code() == 404) {
                        Toast.makeText(this@LoginActivity, "Email Tidak Ditemukan!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@LoginActivity, LoginActivity::class.java))
                        finish()
                    }else if(response.code() == 401) {
                        Toast.makeText(this@LoginActivity, "Password Salah!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@LoginActivity, LoginActivity::class.java))
                        finish()
                    }else{
                        Toast.makeText(this@LoginActivity, "Gagal Login!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@LoginActivity, LoginActivity::class.java))
                        finish()
                    }
                }

                override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                    Toast.makeText(
                        this@LoginActivity,
                        t.localizedMessage,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        }
    }
}