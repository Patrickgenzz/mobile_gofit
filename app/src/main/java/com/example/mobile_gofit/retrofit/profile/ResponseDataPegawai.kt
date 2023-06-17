package com.example.mobile_gofit.retrofit.profile

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseDataPegawai (
    @SerializedName("ID_PEGAWAI")
    @Expose val id:String,

    @SerializedName("NAMA_PEGAWAI")
    @Expose val nama: String,

    @SerializedName("ALAMAT_PEGAWAI")
    @Expose val alamat: String,

    @SerializedName("TANGGAL_LAHIR_PEGAWAI")
    @Expose
    val tanggalLahir: String,

    @SerializedName("NO_TELEPON_PEGAWAI")
    @Expose
    val noTelepon: String
)