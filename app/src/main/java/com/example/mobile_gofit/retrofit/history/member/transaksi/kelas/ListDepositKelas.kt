package com.example.mobile_gofit.retrofit.history.member.transaksi.kelas

import com.google.gson.annotations.SerializedName

data class ListDepositKelas (
    @SerializedName("ID_DEPOSIT_KELAS")
    val id:String,

    @SerializedName("TANGGAL_DEPOSIT_KELAS")
    val tanggal:String,

    @SerializedName("JENIS_KELAS")
    val jenis:String,

    @SerializedName("MASA_BERLAKU_DEPOSIT_KELAS")
    val masaBerlaku:String,

    @SerializedName("JUMLAH_PEMBAYARAN")
    val jumlah:String,

    @SerializedName("TOTAL_DEPOSIT_KELAS")
    val total:Int,
)
