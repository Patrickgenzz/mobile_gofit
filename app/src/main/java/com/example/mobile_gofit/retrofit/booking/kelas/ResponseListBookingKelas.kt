package com.example.mobile_gofit.retrofit.booking.kelas

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class ResponseListBookingKelas (
    @SerializedName("data")
    @Expose val data:List<ListBookingKelas>
)