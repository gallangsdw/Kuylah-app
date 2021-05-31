package com.stackoverthink.kuylahapp.ui.main.itinerary.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.stackoverthink.kuylahapp.R
import com.stackoverthink.kuylahapp.databinding.ItemDetailChildBinding
import com.stackoverthink.kuylahapp.models.Destination

class ItineraryDetailChildAdapter (private val listDestination: ArrayList<Destination>) : RecyclerView.Adapter<ItineraryDetailChildAdapter.ListViewHolder>() {
    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val binding = ItemDetailChildBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.item_detail_child,
            parent,
            false
        )
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val destination = listDestination[position]

        holder.binding.txtDestination.text = destination.nama
        holder.binding.txtHours.text = "IDR ${destination.htmWeekday}"

        holder.itemView.setOnClickListener {
            val action = ItineraryDetailFragmentDirections.actionItineraryDetailFragment2ToDestinationFragment(destination)
            it.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return listDestination.size
    }
}