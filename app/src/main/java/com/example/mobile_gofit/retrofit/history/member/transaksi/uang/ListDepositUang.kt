package com.example.mobile_gofit.retrofit.history.member.transaksi.uang

import com.google.gson.annotations.SerializedName

data class ListDepositUang (
    @SerializedName("ID_DEPOSIT_UANG")
    val id:String,

    @SerializedName("TANGGAL_DEPOSIT_UANG")
    val tanggalDeposit:String,

    @SerializedName("TOTAL_DEPOSIT_UANG")
    val total:String,
)
