package com.example.mobile_gofit.retrofit.instruktur

import com.google.gson.annotations.SerializedName

data class IzinInstruktur (

    @SerializedName("ID_INSTRUKTUR")
    val id:String,

    @SerializedName("TANGGAL_IZIN")
    val tanggalIzin:String,

    @SerializedName("ALASAN_IZIN")
    val alasanIzin:String,

    @SerializedName("STATUS_KONFIRMASI_IZIN")
    val status:String,

)