package com.stackoverthink.kuylahapp.ui.main.itinerary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.stackoverthink.kuylahapp.R
import com.stackoverthink.kuylahapp.databinding.ItemItineraryBinding
import com.stackoverthink.kuylahapp.models.Itinerary


class ItineraryAdapter(private val listItinerary: ArrayList<Itinerary>) : RecyclerView.Adapter<ItineraryAdapter.ListViewHolder>() {
    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemItineraryBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
                R.layout.item_itinerary,
                parent,
                false
        )
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val itinerary =listItinerary[position]
        val builder = StringBuilder()
        for (s in itinerary.category!!){7
            builder.append("$s, ")
        }

        holder.binding.tvTitleItem.text = itinerary.title
        holder.binding.tvDayPrice.text = "${itinerary.day} • ${itinerary.budget}"
        holder.binding.tvCategories.text = builder.toString()

        holder.itemView.setOnClickListener {
            val action = ItineraryFragmentDirections.actionItineraryFragmentToItineraryDetailFragment2(itinerary)
            it.findNavController().navigate(action)
//            Navigation.findNavController(it).navigate(R.id.action_itineraryFragment_to_itineraryDetailFragment2)
        }
    }

    override fun getItemCount(): Int {
        return listItinerary.size
    }
}