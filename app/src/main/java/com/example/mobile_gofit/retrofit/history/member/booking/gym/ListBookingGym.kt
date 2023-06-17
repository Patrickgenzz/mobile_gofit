package com.example.mobile_gofit.retrofit.history.member.booking.gym

import com.google.gson.annotations.SerializedName

data class ListBookingGym (
    @SerializedName("ID_BOOKING_GYM")
    val id:String,

    @SerializedName("TANGGAL_BOOKING_GYM")
    val tanggalBooking:String,

    @SerializedName("TANGGAL_DIBOOKING_GYM")
    val tanggalDibooking:String,

    @SerializedName("WAKTU_PRESENSI_GYM")
    val waktuPresensi:String,

    @SerializedName("SLOT_WAKTU_GYM")
    val slot:Int,

    @SerializedName("STATUS")
    val status:String,
)
