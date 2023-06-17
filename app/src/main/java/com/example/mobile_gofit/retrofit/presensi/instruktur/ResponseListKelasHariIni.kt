package com.example.mobile_gofit.retrofit.presensi.instruktur

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class ResponseListKelasHariIni (
    @SerializedName("data")
    @Expose val data:List<ListKelasHariIni>
)