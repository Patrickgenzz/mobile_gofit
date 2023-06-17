package com.example.mobile_gofit.retrofit.history.member.booking.gym

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseListBookingGym (
    @SerializedName("data")
    @Expose
    val data:List<ListBookingGym>
)