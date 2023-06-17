package com.example.mobile_gofit.retrofit.booking.gym

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class ResponseListBookingGym (
    @SerializedName("data")
    @Expose val data:List<ListBookingGym>
)