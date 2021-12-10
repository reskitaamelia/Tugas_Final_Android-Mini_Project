package com.reskita.tugas_final_android.Network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val Base_URL = "https://masak-apa-tomorisakura.vercel.app/"

    val instance : EndPoint by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(Base_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(EndPoint::class.java)
    }

}