package com.stackoverthink.kuylahapp.ui.main.itinerary.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.LinearLayout.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stackoverthink.kuylahapp.R
import com.stackoverthink.kuylahapp.databinding.ItemDetailParentBinding

class ItineraryDetailParentAdapter(val day: String) : RecyclerView.Adapter<ItineraryDetailParentAdapter.ListViewHolder>() {

    private val viewPool = RecyclerView.RecycledViewPool()

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val binding = ItemDetailParentBinding.bind(itemView)
        val recyclerView: RecyclerView = itemView.findViewById(R.id.rv_detail_child)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.item_detail_parent,
            parent,
            false
        )
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
//        holder.binding.tvDayTitle.text = "Day $position"
        holder.recyclerView.apply {
            layoutManager = LinearLayoutManager(holder.recyclerView.context, VERTICAL, false)
        }
    }

    override fun getItemCount(): Int {
        return day.toInt()
    }
}