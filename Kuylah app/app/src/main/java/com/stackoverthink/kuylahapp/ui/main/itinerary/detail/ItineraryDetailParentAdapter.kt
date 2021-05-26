package com.stackoverthink.kuylahapp.ui.main.itinerary.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stackoverthink.kuylahapp.R
import com.stackoverthink.kuylahapp.databinding.ItemDetailParentBinding
import com.stackoverthink.kuylahapp.models.Destination
import com.stackoverthink.kuylahapp.models.Schedule

class ItineraryDetailParentAdapter(private val listSchedule: ArrayList<Schedule>) : RecyclerView.Adapter<ItineraryDetailParentAdapter.ListViewHolder>() {

    private val viewPool = RecyclerView.RecycledViewPool()

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemDetailParentBinding.bind(itemView)

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
        val schedule = listSchedule[position]
        holder.binding.tvDayTitle.text = "Day ${position + 1}"
        holder.binding.rvDetailChild.apply {
            layoutManager = LinearLayoutManager(holder.binding.rvDetailChild.context)
            adapter = ItineraryDetailChildAdapter(schedule.schedules!!)
//            layoutManager = LinearLayoutManager(holder.binding.rvDetailChild.context, LinearLayout.VERTICAL, false)

        }
    }

    override fun getItemCount(): Int {
        return listSchedule.size
    }
}