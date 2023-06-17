package com.example.mobile_gofit.retrofit.jadwal

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class ResponseListJadwalHarian (
    @SerializedName("data")
    @Expose val data:List<ListJadwalHarian>
)