package com.example.mobile_gofit.retrofit.history.member.booking.kelas

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseListBookingKelas (
    @SerializedName("data")
    @Expose
    val data:List<ListBookingKelas>
)