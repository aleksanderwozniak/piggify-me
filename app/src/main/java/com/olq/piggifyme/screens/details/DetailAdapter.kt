package com.olq.piggifyme.screens.details

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.olq.piggifyme.R
import org.jetbrains.anko.find

/**
 * Created by olq on 18.11.17.
 */
class DetailAdapter(private val data: List<Pair<String, Int>>)
    : RecyclerView.Adapter<DetailAdapter.ViewHolder>() {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindDetailView(data[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.adapter_detail_view, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val nameView = view.find<TextView>(R.id.detailNameTextView)
        private val valueView = view.find<TextView>(R.id.detailValueTextView)

        fun bindDetailView(detail: Pair<String, Int>) {
            nameView.text = detail.first
            valueView.text = detail.second.toString()
        }
    }
}