package com.stackoverthink.kuylahapp.ui.main.itinerary

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.stackoverthink.kuylahapp.R
import com.stackoverthink.kuylahapp.databinding.ItemItineraryBinding
import com.stackoverthink.kuylahapp.models.Itinerary
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList


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
        Log.d("Ini apa", itinerary.category.toString())
        val builder = StringBuilder()
        for (s in itinerary.category!!){
            builder.append("$s, ")
        }

        val number = itinerary.budget?.toDouble()
        val budget = NumberFormat.getNumberInstance(Locale.US).format(number)
        Log.d("muncul ngab", budget)
        holder.binding.tvTitleItem.text = itinerary.title
        holder.binding.tvDayPrice.text = "${itinerary.day} Hari • Rp. $budget"
        holder.binding.tvCategories.text = builder.dropLast(2).toString()

        holder.itemView.setOnClickListener {
            val action = ItineraryFragmentDirections.actionItineraryFragmentToItineraryDetailFragment2(
                itinerary
            )
            it.findNavController().navigate(action)
//            Navigation.findNavController(it).navigate(R.id.action_itineraryFragment_to_itineraryDetailFragment2)
        }
    }

    override fun getItemCount(): Int {
        return listItinerary.size
    }
}