package com.example.mobile_gofit.retrofit.instruktur

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseIzinRequest (
    @SerializedName("ID_IZIN_INSTRUKTUR")
    @Expose val id:String,

    @SerializedName("ID_INSTRUKTUR")
    @Expose val idInstruktur: String,

    @SerializedName("TANGGAL_PEMBUATAN")
    @Expose val tanggalPembuatan: String,

    @SerializedName("TANGGAL_IZIN")
    @Expose
    val tanggalIzin: String,

    @SerializedName("ALASAN_IZIN")
    @Expose
    val alasanIzin: String,

    @SerializedName("STATUS_KONFIRMASI_IZIN")
    @Expose
    val status: String,

    @SerializedName("TANGGAL_KONFIRMASI")
    @Expose
    val tanggalKonfirmasi: String
)