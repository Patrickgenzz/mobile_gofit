package com.example.mobile_gofit.retrofit.presensi.instruktur

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PresensiInstrukturRequest{
    @SerializedName("ID_INSTRUKTUR")
    @Expose
    var id: String? = null

    @SerializedName("TANGGAL_JADWAL_HARIAN")
    @Expose
    var tanggal: String? = null
}