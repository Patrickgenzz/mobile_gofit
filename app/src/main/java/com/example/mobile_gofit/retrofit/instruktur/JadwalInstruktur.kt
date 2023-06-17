package com.example.mobile_gofit.retrofit.instruktur

import com.google.gson.annotations.SerializedName

data class JadwalInstruktur (
    @SerializedName("TANGGAL_JADWAL_HARIAN")
    val tanggal:String,
)