package com.example.mobile_gofit.retrofit.presensi.member

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class ResponseListKelasMember (
    @SerializedName("data")
    @Expose val data:List<ListKelasMember>
)