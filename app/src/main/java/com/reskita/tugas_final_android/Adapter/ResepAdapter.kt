package com.reskita.tugas_final_android.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.reskita.tugas_final_android.Model.Result
import com.reskita.tugas_final_android.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_resep.view.*

class ResepAdapter(private val listener: Listener): RecyclerView.Adapter<ResepAdapter.Holder>() {

    interface Listener {
        fun onItemClick(receipt: Result)
    }

    private var listResep = mutableListOf<Result>()

    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(receipt: Result, listener: Listener) {
            with(itemView) {
                Picasso.get()
                    .load(receipt.thumb)
                    .into(resepThumb)
                resepTitle.text = receipt.title
                resepLevel.text = "Level : " + receipt.dificulty
                this.setOnClickListener {
                    listener.onItemClick(receipt)
                }
            }
        }
    }

    fun setData(list: MutableList<Result>) {
        this.listResep = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_resep, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(listResep[position], listener)
    }

    override fun getItemCount(): Int {
        return listResep.size
    }


}