package com.example.mobile_gofit.retrofit

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit

object RClient {
    private const val BASE_URL = "https://patrickstore.xyz/backend/"
    val instances:Api by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(Api::class.java)
    }
}