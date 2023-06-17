package com.example.mobile_gofit.jadwal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mobile_gofit.LoginActivity
import com.example.mobile_gofit.R
import com.example.mobile_gofit.databinding.ActivityJadwalHarianBinding

class JadwalHarianActivity : AppCompatActivity() {
    private lateinit var binding: ActivityJadwalHarianBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jadwal_harian)

        binding = ActivityJadwalHarianBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showDataFragment()

        with(binding){
            btnBack.setOnClickListener{
                startActivity(Intent(this@JadwalHarianActivity, LoginActivity::class.java))
                finish()
            }
        }
    }

    private fun showDataFragment() {
        val mFragmentManager = supportFragmentManager
        val mFragmentTransaction = mFragmentManager.beginTransaction()
        val mFragment = JadwalHarianFragment()
        mFragmentTransaction.replace(R.id.fl_data,mFragment).commit()
    }
}