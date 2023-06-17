package com.example.mobile_gofit.retrofit.presensi.instruktur

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

class ResponsePresensiInstrukturRequest {
    @SerializedName("status")
    var status: Int? = null

    @SerializedName("message")
    val message:String? = null
}