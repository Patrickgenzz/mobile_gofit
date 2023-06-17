package com.example.mobile_gofit.retrofit.booking.kelas

import com.google.gson.annotations.SerializedName

data class ListBookingKelas (
    @SerializedName("ID_KELAS")
    val id:String,

    @SerializedName("TANGGAL_JADWAL_HARIAN")
    val tanggal:String,

    @SerializedName("NAMA_INSTRUKTUR")
    val nama:String,

    @SerializedName("HARI_JADWAL_UMUM")
    val hari:String,

    @SerializedName("SESI_JADWAL_UMUM")
    val sesi:Int,

    @SerializedName("KUOTA")
    val kuota:Int,

    @SerializedName("JENIS_KELAS")
    val jenis:String,

    @SerializedName("TARIF_KELAS")
    val tarif:String,
)