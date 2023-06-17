package com.example.mobile_gofit.retrofit.instruktur

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class ResponseJadwalInstruktur (
    @SerializedName("data")
    @Expose val data:List<JadwalInstruktur>
)