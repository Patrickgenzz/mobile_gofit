package com.example.mobile_gofit.member.history.transaksi.uang

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mobile_gofit.R
import com.example.mobile_gofit.databinding.ActivityListUangBinding
import com.example.mobile_gofit.member.history.MenuActivityHistoryMember

class ListActivityUang : AppCompatActivity() {
    private lateinit var binding: ActivityListUangBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_uang)

        binding = ActivityListUangBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showDataFragment()

        with(binding){
           btnBack.setOnClickListener{
               startActivity(Intent(this@ListActivityUang, MenuActivityHistoryMember::class.java))
               finish()
           }
        }
    }

    private fun showDataFragment() {
        val mFragmentManager = supportFragmentManager
        val mFragmentTransaction = mFragmentManager.beginTransaction()
        val mFragment = ListFragmentUang()
        mFragmentTransaction.replace(R.id.fl_data,mFragment).commit()
    }
}