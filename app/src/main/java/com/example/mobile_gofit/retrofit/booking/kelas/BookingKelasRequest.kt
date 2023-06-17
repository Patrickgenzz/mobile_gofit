package com.example.mobile_gofit.retrofit.booking.kelas

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BookingKelasRequest{
    @SerializedName("ID_MEMBER")
    @Expose
    var id: String? = null

    @SerializedName("TANGGAL_DIBOOKING_KELAS")
    @Expose
    var tanggal: String? = null
}