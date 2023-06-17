package com.example.mobile_gofit.retrofit.profile

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseDataInstruktur (
    @SerializedName("ID_INSTRUKTUR")
    @Expose val id:String,

    @SerializedName("NAMA_INSTRUKTUR")
    @Expose val nama: String,

    @SerializedName("ALAMAT_INSTRUKTUR")
    @Expose val alamat: String,

    @SerializedName("TANGGAL_LAHIR_INSTRUKTUR")
    @Expose
    val tanggalLahir: String,

    @SerializedName("NO_TELEPON_INSTRUKTUR")
    @Expose
    val noTelepon: String,

    @SerializedName("TOTAL_KETERLAMBATAN")
    @Expose
    val keterlambatan: Int
)