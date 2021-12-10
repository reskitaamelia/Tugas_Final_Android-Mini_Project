package com.reskita.tugas_final_android.Network

import com.reskita.tugas_final_android.Model.DataResep
import com.reskita.tugas_final_android.Model.DetailResep
import com.reskita.tugas_final_android.Model.Result
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface EndPoint {

    @GET("api/recipes")
    suspend fun getListReceipt(): Response<DataResep>

    @GET
    suspend fun getDetail(@Url url : String) : Response<DetailResep>

}