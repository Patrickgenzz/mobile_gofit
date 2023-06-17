package com.example.mobile_gofit.instruktur.izin

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.ArrayAdapter
import androidx.core.view.isEmpty
import com.example.mobile_gofit.utils.Format

import com.example.mobile_gofit.R
import com.example.mobile_gofit.databinding.ActivityTambahIzinBinding
import com.example.mobile_gofit.member.MenuActivityMember
import com.example.mobile_gofit.retrofit.RClient
import com.example.mobile_gofit.retrofit.instruktur.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TambahActivityIzin : AppCompatActivity() {

    var pref: SharedPreferences? = null
    private val ListJadwal = ArrayList<JadwalInstruktur>()
    private val ListInstruktur = ArrayList<ListInstruktur>()
    private lateinit var binding: ActivityTambahIzinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_izin)

        pref = getSharedPreferences("data", Context.MODE_PRIVATE)
        binding = ActivityTambahIzinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = pref!!.getString("user", "").toString()

        getJadwalInstruktur(id) {
            val listJadwal = mutableListOf("Pilih Tanggal")
            listJadwal.addAll(ListJadwal.map { Format.formatDateTime(it.tanggal) })

            val adapter = ArrayAdapter(this@TambahActivityIzin, android.R.layout.simple_spinner_item, listJadwal)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.tanggal.adapter = adapter
        }

        getListInstruktur(id) {
            val listInstruktur = mutableListOf("Instruktur Pengganti")
            listInstruktur.addAll(ListInstruktur.map { it.nama })

            val adapter = ArrayAdapter(this@TambahActivityIzin, android.R.layout.simple_spinner_item, listInstruktur)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.instruktur.adapter = adapter
        }

        with(binding){
            btnCancel.setOnClickListener {
                startActivity(Intent(this@TambahActivityIzin, IzinActivityInstruktur::class.java))
                finish()
            }

            btnSave.setOnClickListener {
                tambahIzinInstruktur()
            }
        }
    }

    private fun getJadwalInstruktur(id: String, callback: () -> Unit) {
        RClient.instances.getJadwalInstrukturById(id).enqueue(object : Callback<ResponseJadwalInstruktur> {
            override fun onResponse(
                call: Call<ResponseJadwalInstruktur>,
                response: Response<ResponseJadwalInstruktur>
            ) {
                if (response.isSuccessful) {
                    ListJadwal.clear()
                    response.body()?.let {ListJadwal.addAll(it.data) }
                    callback.invoke() // Invoke the callback after setting ListJadwal
                }
            }

            override fun onFailure(call: Call<ResponseJadwalInstruktur>, t: Throwable) {
                Toast.makeText(
                    this@TambahActivityIzin,
                    t.localizedMessage,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun getListInstruktur(id: String, callback: () -> Unit) {
        RClient.instances.getListInstrukturById(id).enqueue(object : Callback<ResponseListInstruktur> {
            override fun onResponse(
                call: Call<ResponseListInstruktur>,
                response: Response<ResponseListInstruktur>
            ) {
                if (response.isSuccessful) {
                    ListInstruktur.clear()
                    response.body()?.let {ListInstruktur.addAll(it.data) }
                    callback.invoke() // Invoke the callback after setting ListJadwal
                }
            }

            override fun onFailure(call: Call<ResponseListInstruktur>, t: Throwable) {
                Toast.makeText(
                    this@TambahActivityIzin,
                    t.localizedMessage,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun tambahIzinInstruktur() {
        with(binding){
            if(ListJadwal.isNullOrEmpty() || ListInstruktur.isNullOrEmpty()){
                Toast.makeText(this@TambahActivityIzin, "Instruktur Tidak Ada Kelas!", Toast.LENGTH_SHORT).show()
                return
            }

            val tglIzin: String = Format.reverseFormatDateTime(tanggal.selectedItem?.toString())
            val instruktur: String? = instruktur.selectedItem?.toString()
            val alasanIzin: String = inputAlasanIzin.text.toString()

            if(tglIzin.isEmpty()){
                Toast.makeText(this@TambahActivityIzin, "Tanggal Izin Tidak Boleh Kosong!", Toast.LENGTH_SHORT).show()
                return
            }else if(tglIzin == "Pilih Tanggal"){
                Toast.makeText(this@TambahActivityIzin, "Pilih Tanggal Terlebih Dahulu!", Toast.LENGTH_SHORT).show()
                return
            }

            if(alasanIzin.isEmpty()){
                Toast.makeText(this@TambahActivityIzin, "Alasan Izin Tidak Boleh Kosong!", Toast.LENGTH_SHORT).show()
                return
            }

            val id = pref!!.getString("user", "")

            val request = IzinRequest()
            request.id = id
            request.tanggalIzin = tglIzin
            request.alasanIzin = alasanIzin
            request.instruktur = instruktur

            RClient.instances.addIzinInstruktur(request).enqueue(object : Callback<ResponseIzinRequest>{
                override fun onResponse(call: Call<ResponseIzinRequest>, response: Response<ResponseIzinRequest> ) {
                    if(response.isSuccessful){
                        Toast.makeText(this@TambahActivityIzin, "Berhasil Menambah Izin Instruktur!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@TambahActivityIzin, IzinActivityInstruktur::class.java))
                        finish()
                    }else if(response.code() == 404) {
                        Toast.makeText(this@TambahActivityIzin, "Maksimal Izin H-1!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@TambahActivityIzin, IzinActivityInstruktur::class.java))
                        finish()
                    }else{
                        Toast.makeText(this@TambahActivityIzin, "Gagal Menambah Izin Instruktur!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@TambahActivityIzin, IzinActivityInstruktur::class.java))
                        finish()
                    }
                }

                override fun onFailure(call: Call<ResponseIzinRequest>, t: Throwable) {
                    Toast.makeText(
                        this@TambahActivityIzin,
                        t.localizedMessage,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        }
    }
}