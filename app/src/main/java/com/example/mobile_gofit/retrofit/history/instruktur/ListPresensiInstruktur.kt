package com.example.mobile_gofit.retrofit.history.instruktur

import com.google.gson.annotations.SerializedName

data class ListPresensiInstruktur (
    @SerializedName("ID_PRESENSI_INSTRUKTUR")
    val id:String,

    @SerializedName("TANGGAL_JADWAL_HARIAN")
    val tanggal:String,

    @SerializedName("WAKTU_MULAI_KELAS")
    val waktuMulai:String,

    @SerializedName("WAKTU_SELESAI_KELAS")
    val waktuSelesai:String,

    @SerializedName("DURASI_KELAS")
    val durasi:Int,

    @SerializedName("KETERLAMBATAN")
    val terlambat:Int,
)
