package com.example.mobile_gofit.retrofit.booking.gym

import com.google.gson.annotations.SerializedName

data class ListBookingGym (

    @SerializedName("ID_BOOKING_GYM")
    val id:String,

    @SerializedName("TANGGAL_BOOKING_GYM")
    val tanggalBooking:String,

    @SerializedName("TANGGAL_DIBOOKING_GYM")
    val tanggalDiBooking:String,

    @SerializedName("WAKTU_PRESENSI_GYM")
    val waktu:String,

    @SerializedName("SLOT_WAKTU_GYM")
    val slot:Int,

)