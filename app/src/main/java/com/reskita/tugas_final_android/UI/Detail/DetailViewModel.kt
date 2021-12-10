package com.reskita.tugas_final_android.UI.Detail

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import com.reskita.tugas_final_android.Model.DataResep
import com.reskita.tugas_final_android.Model.DetailResep
import com.reskita.tugas_final_android.Model.Result
import com.reskita.tugas_final_android.Network.RetrofitClient
import kotlinx.android.synthetic.main.fragment_detail.view.*
import retrofit2.Response
import java.lang.Exception

class DetailViewModel: ViewModel() {
    private val TAG = "DetailViewModel"

    suspend fun getDetail(key: String, view: View) {
        var url = "api/recipe/$key"

        var response: Response<DetailResep>? = null
        try {
            response = RetrofitClient.instance.getDetail(url)
            if(response.isSuccessful) {
                with(view) {
                    val data = response!!.body()!!.results
                    DetailFragment.bahan = getBahan(data.ingredient)
                    DetailFragment.step = getStep(data.step)
                    detailDesc.text = data.desc
                    detailBahan.text = DetailFragment.bahan
                    detailStep.text = DetailFragment.step
                    detailStep.tag = "loaded"
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "${e.message}")
        }
    }

    private fun getStep(step: List<String>): String {
        var txt = ""
        for(stp in step) {
            txt += "$stp\n"
        }
        return txt
    }

    private fun getBahan(ingredient: List<String>): String {
        var txt = ""
        var i = 1
        for(bahan in ingredient) {
            txt += "$i. $bahan\n"
            i++
        }
        return txt
    }
}