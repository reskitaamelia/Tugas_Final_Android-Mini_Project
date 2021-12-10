package com.reskita.tugas_final_android.UI.List

import android.util.Log
import androidx.lifecycle.ViewModel
import com.reskita.tugas_final_android.Adapter.ResepAdapter
import com.reskita.tugas_final_android.Model.DataResep
import com.reskita.tugas_final_android.Model.Result
import com.reskita.tugas_final_android.Network.RetrofitClient
import retrofit2.Response
import java.lang.Exception

class ListViewModel: ViewModel() {
    private val TAG = "ListViewModel"

    suspend fun getListReceipt(adapter: ResepAdapter) {
        var response: Response<DataResep>? = null
        try {
            response = RetrofitClient.instance.getListReceipt()
            if(response.isSuccessful) {
                adapter.setData(response.body()!!.results as MutableList<Result>)
            }
        } catch (e: Exception) {
            Log.e(TAG, "${e.message}")
        }
    }

}