package com.stackoverthink.kuylahapp.ui.main.itinerary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stackoverthink.kuylahapp.R
import com.stackoverthink.kuylahapp.databinding.ItemScheduleBinding

class ItineraryDetailAdapter(private val listDestination: ArrayList<Destination>) : RecyclerView.Adapter<ItineraryDetailAdapter.ListViewHolder>() {
    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemScheduleBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.item_schedule,
            parent,
            false
        )
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val destination = listDestination[position]

        holder.binding.txtDestination.text = destination.name
        holder.binding.txtHours.text = destination.time

    }

    override fun getItemCount(): Int {
        return listDestination.size
    }
}