package com.example.mobile_gofit.retrofit.history.member.transaksi.kelas

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseListDepositKelas (
    @SerializedName("data")
    @Expose
    val data:List<ListDepositKelas>
)