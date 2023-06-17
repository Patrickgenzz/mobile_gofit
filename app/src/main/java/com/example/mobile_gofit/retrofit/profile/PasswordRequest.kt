package com.example.mobile_gofit.retrofit.profile

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PasswordRequest {
    @SerializedName("PASSWORD")
    @Expose
    var passwordLama: String? = null

    @SerializedName("NEW_PASSWORD")
    @Expose
    var passwordBaru: String? = null

    @SerializedName("EMAIL")
    @Expose
    var email: String? = null
}