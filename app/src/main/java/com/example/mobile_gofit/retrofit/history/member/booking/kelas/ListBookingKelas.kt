package com.example.mobile_gofit.retrofit.history.member.booking.kelas

import com.google.gson.annotations.SerializedName

data class ListBookingKelas (
    @SerializedName("ID_BOOKING_KELAS")
    val idBooking:String,

    @SerializedName("TANGGAL_BOOKING_KELAS")
    val tanggalBooking:String,

    @SerializedName("TANGGAL_DIBOOKING_KELAS")
    val tanggalDibooking:String,

    @SerializedName("WAKTU_PRESENSI_KELAS")
    val waktuPresensi:String,

    @SerializedName("TARIF_KELAS")
    val tarif:Int,

    @SerializedName("JENIS_KELAS")
    val jenis:String,

    @SerializedName("ID_KELAS")
    val idKelas:String,

    @SerializedName("STATUS")
    val status:String,
)
