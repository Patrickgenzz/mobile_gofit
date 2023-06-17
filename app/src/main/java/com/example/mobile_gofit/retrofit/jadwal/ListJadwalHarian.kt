package com.example.mobile_gofit.retrofit.jadwal

import com.google.gson.annotations.SerializedName

data class ListJadwalHarian (
    @SerializedName("TANGGAL_JADWAL_HARIAN")
    val tanggal:String,

    @SerializedName("NAMA_INSTRUKTUR")
    val nama:String,

    @SerializedName("JENIS_KELAS")
    val jenis:String,

    @SerializedName("HARI_JADWAL_UMUM")
    val hari:String,

    @SerializedName("SESI_JADWAL_UMUM")
    val sesi:Int,

    @SerializedName("STATUS_KELAS")
    val status:String,

    @SerializedName("TARIF_KELAS")
    val tarif:String,
)