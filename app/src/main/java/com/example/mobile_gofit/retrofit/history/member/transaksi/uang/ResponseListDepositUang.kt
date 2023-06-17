package com.example.mobile_gofit.retrofit.history.member.transaksi.uang

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseListDepositUang (
    @SerializedName("data")
    @Expose
    val data:List<ListDepositUang>
)