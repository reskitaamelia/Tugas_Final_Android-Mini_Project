package com.reskita.tugas_final_android.UI.Detail

import android.util.Log
import androidx.lifecycle.ViewModel
import com.reskita.tugas_final_android.Model.DataResep
import com.reskita.tugas_final_android.Model.DetailResep
import com.reskita.tugas_final_android.Model.Result
import com.reskita.tugas_final_android.Network.RetrofitClient
import retrofit2.Response
import java.lang.Exception

class DetailViewModel: ViewModel() {
    private val TAG = "DetailViewModel"

    suspend fun getDetail(key: String) {
        var url = "api/recipe/$key"

        var response: Response<DetailResep>? = null
        try {
            response = RetrofitClient.instance.getDetail(url)
            if(response.isSuccessful) {

            }
        } catch (e: Exception) {
            Log.e(TAG, "${e.message}")
        }
    }
}