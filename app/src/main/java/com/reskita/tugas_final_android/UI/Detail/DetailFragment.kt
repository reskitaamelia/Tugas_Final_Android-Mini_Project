package com.reskita.tugas_final_android.UI.Detail

import android.content.Intent
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
        detailToolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
        
        Picasso.get()
            .load(selectedReceipt!!.thumb)
            .into(detailThumb)

        detailTitle.text = selectedReceipt!!.title
        detailLevel.text = "Level : " + selectedReceipt!!.dificulty

        lifecycleScope.launch {
            viewModel.getDetail(selectedReceipt!!.key, view)
        }

        shareBtn.setOnClickListener {
            if(detailStep.tag == "loaded") {

                val txt = "${selectedReceipt!!.title}\n\n" +
                        "Bahan : \n" +
                        "$bahan\n\n" +
                        "Langkah-langkah : \n" +
                        "$step"

                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, txt)
                    type = "text/plain"
                }

                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() = DetailFragment()
        var selectedReceipt: Result? = null
        var bahan = ""
        var step = ""
    }
}