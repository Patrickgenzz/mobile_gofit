package com.example.mobile_gofit.retrofit.booking.gym

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BookingGymRequest{
    @SerializedName("ID_MEMBER")
    @Expose
    var id: String? = null

    @SerializedName("TANGGAL_DIBOOKING_GYM")
    @Expose
    var tanggal: String? = null
}