package com.example.mobile_gofit.retrofit.presensi.member

import com.google.gson.annotations.SerializedName

data class ListKelasMember (
    @SerializedName("ID_BOOKING_KELAS")
    val id:String,

    @SerializedName("JENIS_KELAS")
    val jenis:String,

    @SerializedName("TANGGAL_DIBOOKING_KELAS")
    val tanggal:String,

    @SerializedName("NAMA_MEMBER")
    val nama:String,

    @SerializedName("HARI_JADWAL_UMUM")
    val hari:String,

    @SerializedName("STATUS")
    val status:String,

)