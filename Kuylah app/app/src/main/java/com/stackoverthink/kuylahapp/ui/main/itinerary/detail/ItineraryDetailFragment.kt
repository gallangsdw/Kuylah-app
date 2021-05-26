package com.stackoverthink.kuylahapp.ui.main.itinerary.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.stackoverthink.kuylahapp.databinding.FragmentItineraryDetailBinding
import com.stackoverthink.kuylahapp.models.Destination
import com.stackoverthink.kuylahapp.models.Itinerary

class ItineraryDetailFragment : Fragment() {

    private lateinit var binding: FragmentItineraryDetailBinding
    private lateinit var itineraryDetailViewModel: ItineraryDetailViewModel

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

        binding.tvItineraryTitle.text = itinerary.title
        showRecyclerList(itinerary)
    }

    private fun showRecyclerList(itinerary: Itinerary) {
        itineraryDetailViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(ItineraryDetailViewModel::class.java)

        binding.progressBar.visibility = View.VISIBLE
        itineraryDetailViewModel.setData(itinerary.title.toString(), itinerary.day.toString())
        itineraryDetailViewModel.getListDestinations().observe(viewLifecycleOwner, {
                schedule ->
            binding.progressBar.visibility = View.GONE
            if (schedule!=null){
                Log.d("Schedule ${itinerary.day} days", schedule.toString())
                val scheduleAdapter = ItineraryDetailParentAdapter(schedule)
                binding.rvDetailParent.layoutManager = LinearLayoutManager(activity)
                binding.rvDetailParent.adapter = scheduleAdapter
            }
        })
    }


}