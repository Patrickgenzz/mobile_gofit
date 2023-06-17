package com.example.mobile_gofit.retrofit.booking.kelas

import com.google.gson.annotations.SerializedName

class ResponseBookingKelasRequest {
    @SerializedName("status")
    var status: Int? = null

    @SerializedName("message")
    val message:String? = null
}