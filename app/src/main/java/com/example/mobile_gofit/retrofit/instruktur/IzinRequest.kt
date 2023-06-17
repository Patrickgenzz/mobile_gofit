package com.example.mobile_gofit.retrofit.instruktur

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class IzinRequest {
    @SerializedName("ID_INSTRUKTUR")
    @Expose
    var id: String? = null

    @SerializedName("TANGGAL_IZIN")
    @Expose
    var tanggalIzin: String? = null

    @SerializedName("ALASAN_IZIN")
    @Expose
    var alasanIzin: String? = null

    @SerializedName("INSTRUKTUR_PENGGANTI")
    @Expose
    var instruktur: String? = null

}