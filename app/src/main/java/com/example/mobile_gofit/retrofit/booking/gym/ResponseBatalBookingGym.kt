package com.example.mobile_gofit.retrofit.booking.gym

import com.google.gson.annotations.SerializedName

class ResponseBatalBookingGym(
    @SerializedName("status")
    val status: Int? = null,

    @SerializedName("message")
    val message: String? = null
)