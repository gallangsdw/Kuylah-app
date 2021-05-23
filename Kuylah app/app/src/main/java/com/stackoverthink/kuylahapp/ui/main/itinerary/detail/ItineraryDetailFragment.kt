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

        showRecyclerList1()
        showRecyclerList2()
        showRecyclerListLoop()
    }

    private fun showRecyclerListLoop() {
        val itinerary = args.itinerary
        for (i in 1..itinerary.day!!.toInt()){
            itineraryDetailViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(ItineraryDetailViewModel::class.java)

            itineraryDetailViewModel.setDestinations(itinerary.title.toString(), i.toString())
            itineraryDetailViewModel.getListDestinations().observe(viewLifecycleOwner, {
                    destination ->
                if (destination!=null){
                    Log.d("Destination ${itinerary.day}", destination.toString())
//                    val scheduleAdapter = ItineraryDetailAdapter(destination)
//                    binding.rvDay1.layoutManager = LinearLayoutManager(activity)
//                    binding.rvDay1.adapter = scheduleAdapter
                }
            })
        }
    }

    private fun showRecyclerList1() {
        val itinerary = args.itinerary
        itineraryDetailViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(ItineraryDetailViewModel::class.java)

        itineraryDetailViewModel.setDestinations(itinerary.title.toString(), "1")
        itineraryDetailViewModel.getListDestinations().observe(viewLifecycleOwner, {
                destination ->
            if (destination!=null){
//                val scheduleAdapter = ItineraryDetailAdapter(destination)
//                binding.rvDay1.layoutManager = LinearLayoutManager(activity)
//                binding.rvDay1.adapter = scheduleAdapter
            }
        })
    }

    private fun showRecyclerList2() {
        val itinerary = args.itinerary
        itineraryDetailViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(ItineraryDetailViewModel::class.java)

        itineraryDetailViewModel.setDestinations(itinerary.title.toString(), "2")
        itineraryDetailViewModel.getListDestinations().observe(viewLifecycleOwner, {
                destination ->
            if (destination!=null){
//                val scheduleAdapter = ItineraryDetailAdapter(destination)
//                binding.rvDay2.layoutManager = LinearLayoutManager(activity)
//                binding.rvDay2.adapter = scheduleAdapter
            }
        })
    }

}