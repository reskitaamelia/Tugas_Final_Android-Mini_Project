package com.reskita.tugas_final_android.UI.List

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.reskita.tugas_final_android.Adapter.ResepAdapter
import com.reskita.tugas_final_android.R
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.coroutines.launch

class ListFragment : Fragment() {

    private lateinit var viewModel: ListViewModel
    private lateinit var adapter: ResepAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(this)[ListViewModel::class.java]
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ResepAdapter()
        rcView_List_resep.setHasFixedSize(true)
        rcView_List_resep.layoutManager = LinearLayoutManager(requireContext())
        rcView_List_resep.adapter = adapter

        lifecycleScope.launch {
            viewModel.getListReceipt(adapter)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ListFragment()
    }
}