package com.example.mobile_gofit.retrofit.history.instruktur

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseListPresensiInstruktur (
    @SerializedName("data")
    @Expose
    val data:List<ListPresensiInstruktur>
)