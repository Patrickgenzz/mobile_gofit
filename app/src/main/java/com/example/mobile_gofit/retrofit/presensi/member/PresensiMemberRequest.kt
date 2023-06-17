package com.example.mobile_gofit.retrofit.presensi.member

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PresensiMemberRequest{
    @SerializedName("ID_BOOKING_KELAS")
    @Expose
    var id: String? = null
}