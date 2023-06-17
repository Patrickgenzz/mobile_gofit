package com.example.mobile_gofit.retrofit

import com.google.gson.annotations.SerializedName

class ResponseUpdate {
    @SerializedName("status")
    var status: Int? = null

    @SerializedName("message")
    val message:String? = null
}