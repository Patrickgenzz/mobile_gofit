package com.example.mobile_gofit.retrofit

import com.example.mobile_gofit.retrofit.booking.gym.BookingGymRequest
import com.example.mobile_gofit.retrofit.booking.gym.ResponseBatalBookingGym
import com.example.mobile_gofit.retrofit.booking.gym.ResponseBookingGymRequest
import com.example.mobile_gofit.retrofit.booking.gym.ResponseListBookingGym
import com.example.mobile_gofit.retrofit.booking.kelas.BookingKelasRequest
import com.example.mobile_gofit.retrofit.booking.kelas.ResponseBatalBookingKelas
import com.example.mobile_gofit.retrofit.login.ResponseLogin
import com.example.mobile_gofit.retrofit.login.UserRequest

import com.example.mobile_gofit.retrofit.profile.PasswordRequest
import com.example.mobile_gofit.retrofit.profile.ResponseDataInstruktur
import com.example.mobile_gofit.retrofit.profile.ResponseDataMember
import com.example.mobile_gofit.retrofit.profile.ResponseDataPegawai
import com.example.mobile_gofit.retrofit.booking.kelas.ResponseListBookingKelas
import com.example.mobile_gofit.retrofit.booking.kelas.ResponseBookingKelasRequest
import com.example.mobile_gofit.retrofit.history.instruktur.ResponseListPresensiInstruktur
import com.example.mobile_gofit.retrofit.history.member.transaksi.aktivasi.ResponseListAktivasi
import com.example.mobile_gofit.retrofit.history.member.transaksi.kelas.ResponseListDepositKelas
import com.example.mobile_gofit.retrofit.history.member.transaksi.uang.ResponseListDepositUang
import com.example.mobile_gofit.retrofit.instruktur.*

import com.example.mobile_gofit.retrofit.jadwal.ResponseListJadwalHarian
import com.example.mobile_gofit.retrofit.presensi.instruktur.PresensiInstrukturRequest
import com.example.mobile_gofit.retrofit.presensi.instruktur.ResponseListKelasHariIni
import com.example.mobile_gofit.retrofit.presensi.instruktur.ResponsePresensiInstrukturRequest
import com.example.mobile_gofit.retrofit.presensi.member.ResponseListKelasMember
import com.example.mobile_gofit.retrofit.presensi.member.ResponsePresensiMemberRequest
import com.example.mobile_gofit.retrofit.history.member.booking.kelas.ResponseListBookingKelas as ResponseBookingKelas
import com.example.mobile_gofit.retrofit.history.member.booking.gym.ResponseListBookingGym as ResponseBookingGym

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface Api {
    //Autentikasi
    @POST("authentication/login")
    fun login(
        @Body userRequest: UserRequest
    ): Call<ResponseLogin>

    @POST("authentication/gantiPasswordInstruktur")
    fun postGantiPasswordInstruktur(
        @Body passwordRequest: PasswordRequest
    ): Call<ResponseUpdate>

    @POST("authentication/gantiPassword")
    fun postGantiPasswordPegawai(
        @Body passwordRequest: PasswordRequest
    ): Call<ResponseUpdate>

    //Member
    @GET("member/memberById/{id}")
    fun getMemberById(
        @Path("id") id: String? = null
    ): Call<ResponseDataMember>

    //Instruktur
    @GET("instruktur/instrukturById/{id}")
    fun getInstrukturById(
        @Path("id") id: String? = null
    ): Call<ResponseDataInstruktur>

    //Pegawai
    @GET("pegawai/pegawaiById/{id}")
    fun getPegawaiById(
        @Path("id") id: String? = null
    ): Call<ResponseDataPegawai>

    //Izin Instruktur
    @POST("izinInstruktur/create")
    fun addIzinInstruktur(
        @Body izinRequest: IzinRequest
    ): Call<ResponseIzinRequest>

    @GET("izinInstruktur/izinInstrukturById/{id}")
    fun getIzinInstrukturById(
        @Path("id") id: String? = null
    ): Call<ResponseIzinInstruktur>

    @GET("instruktur/jadwalInstrukturById/{id}")
    fun getJadwalInstrukturById(
        @Path("id") id: String? = null
    ): Call<ResponseJadwalInstruktur>

    @GET("instruktur/listInstruktur/{id}")
    fun getListInstrukturById(
        @Path("id") id: String? = null
    ): Call<ResponseListInstruktur>

    //Booking Kelas
    @GET("kelas/bookingKelas")
    fun getListBookingKelas(): Call<ResponseListBookingKelas>

    @POST("presensiBookingKelas/create")
    fun addBookingKelas(
        @Body bookingKelas: BookingKelasRequest
    ): Call<ResponseBookingKelasRequest>

    @PUT("presensiBookingKelas/batalBookingKelas/{idBooking}/{idKelas}")
    fun batalBookingKelas(
        @Path("idBooking") idBooking: String? = null,
        @Path("idKelas") idKelas: String? = null
    ): Call<ResponseBatalBookingKelas>

    //Jadwal
    @GET("jadwalHarian/getJadwalHarian")
    fun getListJadwalHarian(): Call<ResponseListJadwalHarian>

    //Booking Gym
    @GET("presensiBookingGym/getBookingGym/{id}")
    fun getListBookingGym(
        @Path("id") id: String? = null
    ): Call<ResponseListBookingGym>

    @POST("presensiBookingGym/create")
    fun addBookingGym(
        @Body bookingGym: BookingGymRequest
    ): Call<ResponseBookingGymRequest>

    @PUT("presensiBookingGym/batalBookingGym/{id}")
    fun batalBookingGym(
        @Path("id") id: String? = null
    ): Call<ResponseBatalBookingGym>

    //Presensi Instruktur
    @GET("presensiInstruktur/listKelasHariIni")
    fun getListKelasHariIni(): Call<ResponseListKelasHariIni>

    @POST("presensiInstruktur/create")
    fun addPresensiInstruktur(
        @Body presensi: PresensiInstrukturRequest
    ): Call<ResponsePresensiInstrukturRequest>

    //Presensi Booking Kelas Member
    @GET("presensiBookingKelas/bookingKelasById/{id}")
    fun listKelasMember(
        @Path("id") id: String? = null
    ): Call<ResponseListKelasMember>

    @PUT("presensiBookingKelas/update/{id}")
    fun updatePresensiMember(
        @Path("id") id: String? = null
    ): Call<ResponsePresensiMemberRequest>

    //History Aktifitas Instruktur
    @GET("instruktur/listPresensiInstruktur/{id}")
    fun listPresensiInstruktur(
        @Path("id") id: String? = null
    ): Call<ResponseListPresensiInstruktur>

    //History Aktifitas Member
    @GET("member/listBookingKelas/{id}")
    fun listBookingKelas(
        @Path("id") id: String? = null
    ): Call<ResponseBookingKelas>

    @GET("member/listBookingGym/{id}")
    fun listBookingGym(
        @Path("id") id: String? = null
    ): Call<ResponseBookingGym>

    @GET("member/transaksiAktivasi/{id}")
    fun listAktivasi(
        @Path("id") id: String? = null
    ): Call<ResponseListAktivasi>

    @GET("member/transaksiDepositKelas/{id}")
    fun listDepositKelas(
        @Path("id") id: String? = null
    ): Call<ResponseListDepositKelas>

    @GET("member/transaksiDepositUang/{id}")
    fun listDepositUang(
        @Path("id") id: String? = null
    ): Call<ResponseListDepositUang>
}
