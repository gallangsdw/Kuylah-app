package com.stackoverthink.kuylahapp.ui.main.itinerary

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.stackoverthink.kuylahapp.databinding.FragmentItineraryBinding
import com.stackoverthink.kuylahapp.models.Itinerary
import java.util.*

class ItineraryFragment : Fragment() {

    private lateinit var itineraryViewModel: ItineraryViewModel
    private lateinit var binding: FragmentItineraryBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentItineraryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showListItinerary()
    }

    private fun showListItinerary() {
        itineraryViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(ItineraryViewModel::class.java)

        binding.progressBar.visibility = View.VISIBLE
        binding.imgEmpty.visibility = View.GONE

        itineraryViewModel.setItineraries()
        itineraryViewModel.getListItineraries().observe(viewLifecycleOwner,{ itinerary ->
            binding.progressBar.visibility = View.GONE
            binding.imgEmpty.visibility = View.VISIBLE
            if (itinerary!=null && itinerary.size!=0){
                showRecycleList(itinerary)
                binding.imgEmpty.visibility = View.GONE
            }
        })
    }

    private fun showRecycleList(itinerary: ArrayList<Itinerary>) {
        val followAdapter = ItineraryAdapter(itinerary)
        binding.rvItineraries.layoutManager = LinearLayoutManager(activity)
        binding.rvItineraries.adapter = followAdapter
    }

}