package com.example.mobile_gofit.retrofit.login

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseLogin (
    @SerializedName("USER_TYPE")
    @Expose
    var role: String,

    @SerializedName("ID")
    @Expose
    var id: String,

    @SerializedName("EMAIL")
    @Expose
    var email: String
)
