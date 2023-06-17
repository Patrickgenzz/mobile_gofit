package com.example.mobile_gofit.retrofit.history.member.transaksi.aktivasi

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseListAktivasi (
    @SerializedName("data")
    @Expose
    val data:List<ListAktivasi>
)