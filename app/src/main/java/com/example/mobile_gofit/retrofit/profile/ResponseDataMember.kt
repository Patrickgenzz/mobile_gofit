package com.example.mobile_gofit.retrofit.profile

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseDataMember (
    @SerializedName("ID_MEMBER")
    @Expose val id:String,

    @SerializedName("NAMA_MEMBER")
    @Expose val nama: String,

    @SerializedName("ALAMAT_MEMBER")
    @Expose val alamat: String,

    @SerializedName("TANGGAL_LAHIR_MEMBER")
    @Expose
    val tanggalLahir: String,

    @SerializedName("NO_TELEPON_MEMBER")
    @Expose
    val noTelepon: String,

    @SerializedName("STATUS")
    @Expose
    val status: String,

    @SerializedName("SISA_DEPOSIT_UANG")
    @Expose
    val depositUang: String,

    @SerializedName("SISA_DEPOSIT_KELAS")
    @Expose
    val depositKelas: Int,

    @SerializedName("TANGGAL_KADALUARSA")
    @Expose
    val tanggalKadaluarsa: String,
)