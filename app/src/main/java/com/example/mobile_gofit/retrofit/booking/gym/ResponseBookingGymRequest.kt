package com.example.mobile_gofit.retrofit.booking.gym

import com.google.gson.annotations.SerializedName

class ResponseBookingGymRequest {
    @SerializedName("status")
    var status: Int? = null

    @SerializedName("message")
    val message:String? = null
}