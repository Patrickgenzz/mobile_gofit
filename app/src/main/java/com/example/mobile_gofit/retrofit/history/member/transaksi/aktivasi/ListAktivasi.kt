package com.example.mobile_gofit.retrofit.history.member.transaksi.aktivasi

import com.google.gson.annotations.SerializedName

data class ListAktivasi (
    @SerializedName("ID_AKTIVASI")
    val id:String,

    @SerializedName("TANGGAL_AKTIVASI")
    val tanggal:String,

    @SerializedName("JUMLAH_PEMBAYARAN")
    val jumlah:String,

    @SerializedName("MASA_BERLAKU_AKTIVASI")
    val masa:String,
)
