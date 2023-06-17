package com.example.mobile_gofit.retrofit.login

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserRequest {
    @SerializedName("EMAIL")
    @Expose
    var email: String? = null

    @SerializedName("PASSWORD")
    @Expose
    var password: String? = null
}
