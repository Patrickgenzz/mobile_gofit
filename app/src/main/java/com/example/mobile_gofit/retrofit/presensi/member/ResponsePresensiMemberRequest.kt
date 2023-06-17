package com.example.mobile_gofit.retrofit.presensi.member

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

class ResponsePresensiMemberRequest {
    @SerializedName("status")
    var status: Int? = null

    @SerializedName("message")
    val message:String? = null
}