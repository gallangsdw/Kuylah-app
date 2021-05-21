package com.stackoverthink.kuylahapp.ui.main.itinerary.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.stackoverthink.kuylahapp.databinding.FragmentItineraryDetailBinding
import com.stackoverthink.kuylahapp.models.Destination

class ItineraryDetailFragment : Fragment() {

    private lateinit var binding: FragmentItineraryDetailBinding
    private val args by navArgs<ItineraryDetailFragmentArgs>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentItineraryDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itinerary = args.itinerary
        Log.d("Itinerary", itinerary.toString())
//        Log.d("Schedule 1", itinerary.schedule!![0].destination.toString())
//        showRecyclerList(itinerary.schedule!![0].destination)
    }

    private fun showRecyclerList(destination: ArrayList<Destination>) {
        val scheduleAdapter = ItineraryDetailAdapter(destination)
        binding.rvDay1.layoutManager = LinearLayoutManager(activity)
        binding.rvDay1.adapter = scheduleAdapter
        binding.rvDay2.layoutManager = LinearLayoutManager(activity)
        binding.rvDay2.adapter = scheduleAdapter
    }

}