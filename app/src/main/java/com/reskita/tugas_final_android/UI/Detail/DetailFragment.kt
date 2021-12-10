package com.reskita.tugas_final_android.UI.Detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.reskita.tugas_final_android.Model.Result
import com.reskita.tugas_final_android.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.coroutines.launch

class DetailFragment : Fragment() {

    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Picasso.get()
            .load(selectedReceipt!!.thumb)
            .into(detailThumb)

        detailTitle.text = selectedReceipt!!.title
        detailLevel.text = "Level : " + selectedReceipt!!.dificulty

        lifecycleScope.launch {
            viewModel.getDetail(selectedReceipt!!.key, view)
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() = DetailFragment()
        var selectedReceipt: Result? = null
    }
}